package com.tedu.sp07.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HystrixController {
    //在主程序中添加了一個getRestTemplate()方法
    //在該方法中新建了一個RestTemplate實例
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/item-service/{orderId}")
    @HystrixCommand(fallbackMethod = "getItemsFB")
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
        return restTemplate.getForObject("http://item-service/{1}", JsonResult.class, orderId);
    }

    @PostMapping("/item-service/decreaseNumber")
    @HystrixCommand(fallbackMethod = "decreaseNumberFB")
    public JsonResult decreaseNumber(@RequestBody List<Item> items) {
        //發送post請示
        /*return restTemplate.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);*/
        return restTemplate.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    @HystrixCommand(fallbackMethod = "getUserFB")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        /*return restTemplate.getForObject("http://localhost:8101/{1}", JsonResult.class, userId);*/
        return restTemplate.getForObject("http://user-service/{1}", JsonResult.class, userId);
    }

    @GetMapping("/user-service/{userId}/score")
    @HystrixCommand(fallbackMethod = "addScoreFB")
    public JsonResult addScore(@PathVariable Integer userId, Integer score) {
        /*return restTemplate.getForObject("http://localhost:8101/{1}/score?score={2}", JsonResult.class, userId, score);*/
        return restTemplate.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    @HystrixCommand(fallbackMethod = "getOrderFB")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        /*return restTemplate.getForObject("http://localhost:8201/{1}", JsonResult.class, orderId);*/
        return restTemplate.getForObject("http://order-service/{1}", JsonResult.class, orderId);
    }

    @GetMapping("/order-service")
    @HystrixCommand(fallbackMethod = "addOrderFB")
    public JsonResult addOrder() {
        /*return restTemplate.getForObject("http://localhost:8201", JsonResult.class);*/
        return restTemplate.getForObject("http://order-service", JsonResult.class);
    }

    //降級方法的參數和返回值，需要和原始方法一致，方法名任意
    public JsonResult<List<Item>> getItemsFB(String orderId) {
        return JsonResult.error("獲取訂單商品列表失敗");
    }

    public JsonResult decreaseNumberFB(List<Item> items) {
        return JsonResult.error("更新商品庫存失敗");
    }

    public JsonResult<User> getUserFB(Integer userId) {
        return JsonResult.error("獲取用戶信息失敗");
    }

    public JsonResult addScoreFB(Integer userId, Integer score) {
        return JsonResult.error("增加用戶積分失敗");
    }

    public JsonResult<Order> getOrderFB(String orderId) {
        return JsonResult.error("獲取訂單失敗");
    }

    public JsonResult addOrderFB() {
        return JsonResult.error("添加訂單失敗");
    }

}
