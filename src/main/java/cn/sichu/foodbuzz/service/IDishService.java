package cn.sichu.foodbuzz.service;

import cn.sichu.foodbuzz.dto.DishDto;
import cn.sichu.foodbuzz.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
public interface IDishService extends IService<Dish> {

    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);

    void deleteByIds(List<Long> ids);
}
