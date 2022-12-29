package cn.sichu.foodbuzz.service;

import cn.sichu.foodbuzz.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
public interface IOrdersService extends IService<Orders> {
    /**
     * 用户下单
     *
     * @param orders 订单
     */
    public void submit(Orders orders);
}
