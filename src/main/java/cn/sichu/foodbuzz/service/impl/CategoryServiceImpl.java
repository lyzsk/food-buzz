package cn.sichu.foodbuzz.service.impl;

import cn.sichu.foodbuzz.common.CustomException;
import cn.sichu.foodbuzz.entity.Category;
import cn.sichu.foodbuzz.entity.Dish;
import cn.sichu.foodbuzz.entity.Setmeal;
import cn.sichu.foodbuzz.mapper.CategoryMapper;
import cn.sichu.foodbuzz.service.ICategoryService;
import cn.sichu.foodbuzz.service.IDishService;
import cn.sichu.foodbuzz.service.ISetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements ICategoryService {

    @Autowired
    private IDishService dishService;

    @Autowired
    private ISetmealService setmealService;

    /**
     * 根据id删除分类, 在删除前需要判断是否已关联dish/setmeal
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        // dish表中已关联分类id
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper =
            new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        long dishCount = dishService.count(dishLambdaQueryWrapper);
        if (dishCount > 0) {
            throw new CustomException("当前分类已经关联了菜品, 无法删除");
        }
        // setmeal表中已关联分类id
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper =
            new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        long setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0) {
            throw new CustomException("当前分类已经关联了套餐, 无法删除");
        }
        // 正常删除
        super.removeById(id);
    }
}
