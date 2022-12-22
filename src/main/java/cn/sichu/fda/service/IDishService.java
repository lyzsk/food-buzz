package cn.sichu.fda.service;

import cn.sichu.fda.dto.DishDto;
import cn.sichu.fda.entity.Dish;
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
