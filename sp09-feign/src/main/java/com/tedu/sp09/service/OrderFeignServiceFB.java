package com.tedu.sp09.service;

import com.tedu.sp01.pojo.Order;
import com.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignServiceFB implements OrderFeignService{

    @Override
    public JsonResult<Order> getOrder(String orderId) {
        return JsonResult.error("無法獲取商品訂單");
    }

    @Override
    public JsonResult addOrder() {
        return JsonResult.error("無法保存訂單");
    }
}
