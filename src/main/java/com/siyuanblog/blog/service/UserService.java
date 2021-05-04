package com.siyuanblog.blog.service;

import com.siyuanblog.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
