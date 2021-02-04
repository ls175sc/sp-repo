package com.tedu.sp02.item.controller;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;
import com.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    //為了後續測試，觀察是哪一臺服務器執行
    //把yml配置的端口號注入進來
    @Value("${server.port}")
    private int port;

    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
        log.info("server.port=" + port + ", orderId=" + orderId);
        //設置隨機延遲
        int t = new Random().nextInt(5000);
        log.info("Time: "+t);
        //60%概率以內時，執行超時。
        double random = Math.random();
        log.info("Random= "+random);
        if ( random< 0.6) {
            log.info("item-service-" + port + " - 暫停 " + t);
            Thread.sleep(t);
        }
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.okay(items).msg("port=" + port);
    }

    /*
     * 普通的 post 请求 http协议:
     * 协议头
     * asdfasdf
     * asdfasdfas
     * dfasdf
     *
     * 协议体
     * parm1=1&param2=2&param3=3
     *
     * request.getParameter("parm1")
     *
     * @Controller
     * void f(String parm1, int param2)
     *
     *
     * 请求 http协议:
     * 协议头
     * asdfasdf
     * asdfasdfas
     * dfasdf
     *
     * 协议体
     * [{"id":1, "name":....}, {}, {}]
     *
     * @RequestBody
     */
    @PostMapping(value = "/decreaseNumber"/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public JsonResult decreaseNumber(@RequestBody List<Item> items) {
        itemService.decreaseNumbers(items);
        return JsonResult.okay();
    }
}
