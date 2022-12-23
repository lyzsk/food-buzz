package cn.sichu.fda.service.impl;

import cn.sichu.fda.common.BaseContext;
import cn.sichu.fda.common.CustomException;
import cn.sichu.fda.entity.*;
import cn.sichu.fda.mapper.OrdersMapper;
import cn.sichu.fda.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements IOrdersService {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAddressBookService addressBookService;

    @Autowired
    private IOrderDetailService orderDetailService;

    /**
     * 用户下单
     * 获取当前用户id
     * 查询当前用户购物车数据
     * 向orders表和order_detail表传数据下单
     * 清空购物车数据
     *
     * @param orders 订单
     */
    @Transactional
    @Override
    public void submit(Orders orders) {
        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper =
            new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);
        List<ShoppingCart> shoppingCartList =
            shoppingCartService.list(queryWrapper);
        if (shoppingCartList == null || shoppingCartList.size() == 0) {
            throw new CustomException("购物车为空, 不能下单");
        }
        User user = userService.getById(currentId);
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) {
            throw new CustomException("用户地址信息有误，不能下单");
        }
        // 通过MP生成订单号
        long orderId = IdWorker.getId();
        // 订单金额
        AtomicInteger amount = new AtomicInteger(0);

        List<OrderDetail> orderDetailList =
            shoppingCartList.stream().map(item -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(orderId);
                orderDetail.setNumber(item.getNumber());
                orderDetail.setDishFlavor(item.getDishFlavor());
                orderDetail.setDishId(item.getDishId());
                orderDetail.setSetmealId(item.getSetmealId());
                orderDetail.setName(item.getName());
                orderDetail.setImage(item.getImage());
                orderDetail.setAmount(item.getAmount());
                amount.addAndGet(
                    item.getAmount().multiply(new BigDecimal(item.getNumber()))
                        .intValue());
                return orderDetail;
            }).collect(Collectors.toList());

        orders.setNumber(String.valueOf(orderId));
        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(currentId);
        orders.setUserName(user.getName());
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setAddress((addressBook.getProvinceName() == null ? "" :
            addressBook.getProvinceName()) + (
            addressBook.getCityName() == null ? "" : addressBook.getCityName())
            + (addressBook.getDistrictName() == null ? "" :
            addressBook.getDistrictName()) + (addressBook.getDetail() == null ?
            "" : addressBook.getDetail()));
        this.save(orders);

        orderDetailService.saveBatch(orderDetailList);
        shoppingCartService.remove(queryWrapper);
    }
}
