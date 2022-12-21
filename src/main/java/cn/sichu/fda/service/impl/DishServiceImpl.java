package cn.sichu.fda.service.impl;

import cn.sichu.fda.dto.DishDto;
import cn.sichu.fda.entity.Dish;
import cn.sichu.fda.entity.DishFlavor;
import cn.sichu.fda.mapper.DishMapper;
import cn.sichu.fda.service.IDishFlavorService;
import cn.sichu.fda.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements IDishService {
    @Autowired
    private IDishFlavorService dishFlavorService;

    /**
     * 因为涉及多表操作, 记得要加 Transactional
     *
     * @param dishDto
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        // 保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        // 保存菜品口味到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }
}
