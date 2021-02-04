package com.tedu.sp09.service;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//通過指定服務id,可以知道向哪一臺主機來轉發調用
//http://localhost:8001
//http://localhost:8002
@FeignClient(value = "item-service", fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
    /*
    Feign利用Springmvc註解來反向生成訪問路徑
    根據在mapping中指定的路徑，在主機地址後面拼接路徑
    http://localhost:8001/{orderId}
    根據PathVariable配置，把參數數據，拼接到路徑當中
    假設參數是“123”    http://localhost:8001/123
    向拼接的路徑來轉發調用
     */
    @GetMapping("/{orderId}")
    JsonResult<List<Item>> getItems(@PathVariable Integer orderId);

    /*
    根據配置，拼接下面路徑，並向下面路徑轉發請求,在協議體中攜帶商品參數
    http://localhost:8001/decreaseNumber
     */
    @PostMapping("/decreaseNumber")
    JsonResult decreaseNumber(@RequestBody List<Item> items);
}
