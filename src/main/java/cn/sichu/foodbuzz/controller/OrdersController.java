package cn.sichu.foodbuzz.controller;

import cn.sichu.foodbuzz.common.Result;
import cn.sichu.foodbuzz.entity.Orders;
import cn.sichu.foodbuzz.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return Result.success("提交成功");
    }
}
