package com.tedu.sp09.service;

import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class UserFeignServiceFB implements UserFeignService{

    @Override
    public JsonResult<User> getUser(Integer userId) {
        return JsonResult.error("無法獲取用戶信息");
    }

    @Override
    public JsonResult addScore(Integer userId, Integer score) {
        return JsonResult.error("無法增加用戶積分");
    }
}
