package cn.sichu.fda.service;

import cn.sichu.fda.dto.SetmealDto;
import cn.sichu.fda.entity.Setmeal;
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
public interface ISetmealService extends IService<Setmeal> {

    /**
     * 新增套餐, 同时保存套餐和菜品的信息
     *
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐, 同时删除套餐关联的数据
     *
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
