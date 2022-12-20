package cn.sichu.fda.service.impl;

import cn.sichu.fda.entity.Orders;
import cn.sichu.fda.mapper.OrdersMapper;
import cn.sichu.fda.service.IOrdersService;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
