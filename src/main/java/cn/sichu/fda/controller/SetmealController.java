package cn.sichu.fda.controller;

import cn.sichu.fda.common.Result;
import cn.sichu.fda.dto.SetmealDto;
import cn.sichu.fda.entity.Category;
import cn.sichu.fda.entity.Setmeal;
import cn.sichu.fda.service.ICategoryService;
import cn.sichu.fda.service.ISetmealDishService;
import cn.sichu.fda.service.ISetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private ISetmealService setmealService;

    @Autowired
    private ISetmealDishService setmealDishService;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 新增套餐
     *
     * @param setmealDto
     * @return Result<String>
     */
    @PostMapping
    public Result<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息: {}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return Result.success("保存套餐成功");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, queryWrapper);
        // 拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map(item -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return Result.success(dtoPage);
    }

    @PostMapping("/status/{status}")
    public Result<String> status(@PathVariable Integer status,
        @RequestParam("ids") List<Long> ids) {
        Setmeal setmeal = new Setmeal();
        for (Long id : ids) {
            setmeal.setId(id);
            setmeal.setStatus(status);
            setmealService.updateById(setmeal);
        }
        return Result.success("修改成功");
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids) {
        setmealService.removeWithDish(ids);
        return Result.success("删除套餐成功");
    }
}
