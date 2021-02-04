package com.tedu.sp04.order.feignclient;

import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class UserFeignServiceFB implements UserFeignService {
    @Override
    public JsonResult<User> getUser(String userId) {
        if (Math.random() < 0.4) {
            return JsonResult.okay(new User(Integer.parseInt(userId), "緩存 name" + userId, "緩存 pwd" + userId));
        }
        return JsonResult.error("無法獲取用戶信息");
    }

    @Override
    public JsonResult addScore(String userId, String score) {
        return JsonResult.error("無法增加用戶積分");
    }
}
