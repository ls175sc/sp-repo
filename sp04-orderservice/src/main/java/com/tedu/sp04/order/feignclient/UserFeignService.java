package com.tedu.sp04.order.feignclient;

import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", fallback = UserFeignServiceFB.class)
public interface UserFeignService {
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable String userId);

    @GetMapping("/{userId}/score")
    JsonResult addScore(@PathVariable String userId, @RequestParam String score);
}
