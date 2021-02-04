package com.tedu.sp04.order.service;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.OrderService;
import com.tedu.sp04.order.feignclient.ItemFeignService;
import com.tedu.sp04.order.feignclient.UserFeignService;
import com.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    //feign動態生成的接口實現類
    @Autowired
    private ItemFeignService itemService;
    @Autowired
    private UserFeignService userService;

    /*根據訂單id獲取訂單數據，要包含用戶信息，商品信息*/
    @Override
    public Order getOrder(String orderId) {
        //調用user-service獲取用戶信息
        JsonResult<User> user = userService.getUser("7");
        //調用item-service獲取商品信息
        JsonResult<List<Item>> items = itemService.getItems(orderId);
        Order order = new Order();
        order.setId(orderId);
        order.setUser(user.getData());
        order.setItems(items.getData());
        return order;
    }

    /*添加訂單時要修改商品庫存以及用戶積分*/
    @Override
    public void addOrder(Order order) {
        //調用item-service減少商品庫存
        itemService.decreaseNumber(order.getItems());
        //調用user-service增加用戶積分
        userService.addScore("7", "100");
        log.info("保存訂單: " + order);
    }

}
