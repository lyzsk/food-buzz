package cn.sichu.fda.service.impl;

import cn.sichu.fda.common.CustomException;
import cn.sichu.fda.dto.SetmealDto;
import cn.sichu.fda.entity.Setmeal;
import cn.sichu.fda.entity.SetmealDish;
import cn.sichu.fda.mapper.SetmealMapper;
import cn.sichu.fda.service.ISetmealDishService;
import cn.sichu.fda.service.ISetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements ISetmealService {

    @Autowired
    private ISetmealDishService setmealDishService;

    /**
     * 因为要操作两张表, 记得加事务注解
     *
     * @param setmealDto
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map(item -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids).eq(Setmeal::getStatus, 1);
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中, 不能删除");
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> dishLambdaQueryWrapper =
            new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(dishLambdaQueryWrapper);
    }

}
