package cn.sichu.fda.controller;

import cn.sichu.fda.common.Result;
import cn.sichu.fda.dto.DishDto;
import cn.sichu.fda.entity.Category;
import cn.sichu.fda.entity.Dish;
import cn.sichu.fda.entity.DishFlavor;
import cn.sichu.fda.service.ICategoryService;
import cn.sichu.fda.service.IDishFlavorService;
import cn.sichu.fda.service.IDishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 * <p>
 * 1. 改造list方法, 先从Redis中获取菜品数据, 如果有直接返回, 否则查询数据库并把查询到的结果加入redis
 * 2. 改造save和update方法, 加入清除redis缓存的逻辑
 *
 * @author sichu
 * @since 2022-12-20
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService dishService;

    @Autowired
    private IDishFlavorService dishFlavorService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品, 注意要用到 DishDto, 而不能只用 Dish
     * 因为 Dish 里没有 flavor 的属性
     *
     * @return Result
     */
    @PostMapping
    public Result<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        // 根据category清除redis缓存
        Object key = redisTemplate.opsForValue().get("dish_" + dishDto.getCategoryId() + "_" + dishDto.getStatus());
        redisTemplate.delete(key);
        return Result.success("新增菜品成功");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name) {
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, queryWrapper);
        // 对象拷贝, 把 Dish 的 pageInfo 拷贝到 DishDto 的 Page 对象里
        // 再通过 dishDtoPage 获取 categoryName 字段
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return Result.success(dishDtoPage);
    }

    @GetMapping("/{id}")
    public Result<DishDto> get(@PathVariable Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return Result.success(dishDto);
    }

    @PutMapping
    public Result<String> update(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.updateWithFlavor(dishDto);

        // 按category清除redis缓存
        String key = "dish_" + dishDto.getCategoryId() + "_" + dishDto.getStatus();
        redisTemplate.delete(key);
        return Result.success("修改菜品成功");
    }

    /**
     * 根据条件查询对应的菜品数据
     * 这个方法在客户端也会被调用
     * 不能只传Dish, 需要用到DishDto里的flavors集合
     *
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public Result<List<DishDto>> list(Dish dish) {
        List<DishDto> dishDtoList = null;
        // 动态构造redis key
        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();
        // 先从redis中缓存数据
        dishDtoList = (List<DishDto>)redisTemplate.opsForValue().get(key);
        // 如果redis存在缓存, 直接返回
        if (dishDtoList != null) {
            log.info("从redis缓存中提取数据");
            return Result.success(dishDtoList);
        }
        // 否则开始查询
        log.info("redis缓存中没有数据, 开始查询...");
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId()).eq(Dish::getStatus, 1)
            .orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());
        // 查询结束后把信息缓存到redis中
        redisTemplate.opsForValue().set(key, dishDtoList, 60, TimeUnit.MINUTES);
        return Result.success(dishDtoList);
    }
    // public Result<List<DishDto>> list(Dish dish) {
    //     LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
    //     queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
    //     queryWrapper.eq(Dish::getStatus, 1);
    //     queryWrapper.orderByDesc(Dish::getUpdateTime);
    //     List<Dish> list = dishService.list(queryWrapper);
    //     List<DishDto> dishDtoList = list.stream().map(item -> {
    //         DishDto dishDto = new DishDto();
    //         BeanUtils.copyProperties(item, dishDto);
    //         Long categoryId = item.getCategoryId();
    //         Category category = categoryService.getById(categoryId);
    //         if (category != null) {
    //             String categoryName = category.getName();
    //             dishDto.setCategoryName(categoryName);
    //         }
    //         Long dishId = item.getId();
    //         LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
    //         dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
    //         List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
    //         dishDto.setFlavors(dishFlavorList);
    //         return dishDto;
    //     }).collect(Collectors.toList());
    //     return Result.success(dishDtoList);
    // }

    // @GetMapping("/list")
    // public Result<List<Dish>> list(Dish dish) {
    //     LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
    //     queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId,
    //         dish.getCategoryId());
    //     queryWrapper.eq(Dish::getStatus, 1);
    //     queryWrapper.orderByDesc(Dish::getUpdateTime);
    //     List<Dish> list = dishService.list(queryWrapper);
    //     return Result.success(list);
    // }

    @PostMapping("/status/{status}")
    public Result<String> status(@PathVariable("status") Integer status, @RequestParam List<Long> ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ids != null, Dish::getId, ids);
        List<Dish> list = dishService.list(queryWrapper);
        for (Dish dish : list) {
            if (dish != null) {
                dish.setStatus(status);
                dishService.updateById(dish);
            }
        }
        return Result.success("售卖状态修改成功");
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam("ids") List<Long> ids) {
        // 先逻辑删除菜品
        dishService.deleteByIds(ids);
        // 再逻辑删除菜品对应口味
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DishFlavor::getDishId, ids);
        dishFlavorService.remove(queryWrapper);
        return Result.success("菜品删除成功");
    }
}
