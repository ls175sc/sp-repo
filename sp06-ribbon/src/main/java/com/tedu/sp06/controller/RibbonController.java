package com.tedu.sp06.controller;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class RibbonController {
    //在主程序中添加了一個getRestTemplate()方法
    //在該方法中新建了一個RestTemplate實例
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
        //向指定微服務地址發送get請示，並獲得該服務的返回結果
        //（1）占位符，用orderId填充
        /*return restTemplate.getForObject("http://localhost:8001/{1}", JsonResult.class, orderId);*/
        /*
        從註冊中心得到的item-service註冊信息：
        item-service
            localhost:8001
            localhost:8002
         */
        return restTemplate.getForObject("http://item-service/{1}",JsonResult.class,orderId);
    }

    @PostMapping("/item-service/decreaseNumber")
    public JsonResult decreaseNumber(@RequestBody List<Item> items) {
        //發送post請示
        /*return restTemplate.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);*/
        return restTemplate.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        /*return restTemplate.getForObject("http://localhost:8101/{1}", JsonResult.class, userId);*/
        return restTemplate.getForObject("http://user-service/{1}", JsonResult.class, userId);
    }

    @GetMapping("/user-service/{userId}/score")
    public JsonResult addScore(@PathVariable Integer userId, Integer score) {
        /*return restTemplate.getForObject("http://localhost:8101/{1}/score?score={2}", JsonResult.class, userId, score);*/
        return restTemplate.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        /*return restTemplate.getForObject("http://localhost:8201/{1}", JsonResult.class, orderId);*/
        return restTemplate.getForObject("http://order-service/{1}", JsonResult.class, orderId);
    }

    @GetMapping("/order-service")
    public JsonResult addOrder() {
        /*return restTemplate.getForObject("http://localhost:8201", JsonResult.class);*/
        return restTemplate.getForObject("http://order-service", JsonResult.class);
    }
}
