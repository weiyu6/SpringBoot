package com.wytest.springboot.controller;

import com.wytest.springboot.bean.User;
import com.wytest.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * @author weiyu
 * @date 2021/1/22
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String hello(String id){
        logger.debug("kaishi:id{}",id);
        String hello = "hello world!"+id;
        User user = userMapper.selectById(id);
        String name = user.getName();
        return hello+name;
    }
}
