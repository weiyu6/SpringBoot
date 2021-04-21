package com.wytest.springboot.service;

import com.wytest.springboot.bean.User;

/**
 * TODO
 * @author weiyu
 * @date 2021/4/13
 */
public interface UserService {

    public Object qryUser(User u);
    public String add(User user);
}
