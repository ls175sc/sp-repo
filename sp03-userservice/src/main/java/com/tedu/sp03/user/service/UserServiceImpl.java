package com.tedu.sp03.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.UserService;
import com.tedu.web.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@RefreshScope
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    //yml配置的測試用戶數據，JSON字符串注入進來
    @Value("${sp.user-service.users}")
    private String userJson;

    @Override
    public User getUser(Integer id) {
        log.info("users json string : " + userJson);
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {
        });
        for (User u : list) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return new User(id, "name-" + id, "pwd-" + id);
    }

    @Override
    public void addScore(Integer id, Integer score) {
        //TODO 這里增加積分
        log.info("user " + id + " - 增加積分 " + score);
    }
}
