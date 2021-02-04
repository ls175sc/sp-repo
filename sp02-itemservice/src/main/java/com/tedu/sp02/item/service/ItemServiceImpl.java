package com.tedu.sp02.item.service;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    //根據訂單編號，獲得訂單中的商品列表
    @Override
    public List<Item> getItems(String orderId) {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1, "商品 1", 1));
        list.add(new Item(2, "商品 2", 2));
        list.add(new Item(3, "商品 3", 3));
        list.add(new Item(4, "商品 4", 4));
        list.add(new Item(5, "商品 5", 5));
        return list;
    }

    //減少庫存
    @Override
    public void decreaseNumbers(List<Item> list) {
        if (log.isInfoEnabled()) {
            for (Item item : list) {
                log.info("減少庫存 - " + item);
            }
        }
    }
}
