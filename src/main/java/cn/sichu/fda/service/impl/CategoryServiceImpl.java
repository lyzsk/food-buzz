package cn.sichu.fda.service.impl;

import cn.sichu.fda.entity.Category;
import cn.sichu.fda.mapper.CategoryMapper;
import cn.sichu.fda.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
