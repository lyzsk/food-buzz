package cn.sichu.fda.service.impl;

import cn.sichu.fda.entity.Dish;
import cn.sichu.fda.mapper.DishMapper;
import cn.sichu.fda.service.IDishService;
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
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

}
