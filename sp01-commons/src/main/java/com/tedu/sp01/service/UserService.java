package com.tedu.sp01.service;

import com.tedu.sp01.pojo.User;

public interface UserService {
    User getUser(String id);

    void addScore(String id, String score);
}
