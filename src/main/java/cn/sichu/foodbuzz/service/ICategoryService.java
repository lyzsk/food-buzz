package cn.sichu.foodbuzz.service;

import cn.sichu.foodbuzz.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
public interface ICategoryService extends IService<Category> {
    public void remove(Long id);
}
