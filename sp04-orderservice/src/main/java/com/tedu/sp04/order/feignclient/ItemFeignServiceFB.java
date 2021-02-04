package com.tedu.sp04.order.feignclient;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemFeignServiceFB implements ItemFeignService {
    @Override
    public JsonResult<List<Item>> getItems(String orderId) {
        if (Math.random() < 0.5) {
            return JsonResult.okay().data(
                    Arrays.asList(new Item[]{
                            new Item(1, "緩存aaa", 2),
                            new Item(2, "緩存bbb", 1),
                            new Item(3, "緩存ccc", 3),
                            new Item(4, "緩存ddd", 1),
                            new Item(5, "緩存eee", 5),
                    })
            );
        }
        return JsonResult.error("無法獲取訂單商品列表");
    }

    @Override
    public JsonResult decreaseNumber(List<Item> items) {
        return JsonResult.error("無法修改商品庫存");
    }
}
