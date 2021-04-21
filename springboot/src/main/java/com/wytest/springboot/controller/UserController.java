package com.wytest.springboot.controller;

import com.wytest.springboot.bean.User;
import com.wytest.springboot.mapper.UserMapper;
import com.wytest.springboot.service.UserService;
import com.wytest.springboot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * @author weiyu
 * @date 2021/1/29
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;

    @RequestMapping("qryUser")
    public Object qryUser(@RequestBody User u){
        logger.debug("user:{}",u.toString());
        Object user = userService.qryUser(u);
        return user;
    }

    @RequestMapping("updUser")
    public String updUser(@RequestBody User user){
        logger.debug("开始更新用户信息！");
        userMapper.updUser(user);
        redisUtil.set(user.getId(), user);
        logger.debug("updUser.end");
        return "更新成功！";
    }

    @RequestMapping("delUser")
    public String delUser(@RequestBody User u){
        String id = u.getId();
        int i = userMapper.delUser(id);
        redisUtil.del(id);
        logger.debug("删除缓存");
        return "删除成功！";
    }

    @RequestMapping("insertUser")
    public String insertUser(@RequestBody User user){
        userMapper.insertUser(user);
        redisUtil.set(user.getId(),user);
        logger.debug("新增成功！");
        return "新增成功";
    }

    @RequestMapping("qry")
    public User qry(String id){
        User user = userMapper.selectById(id);
        return user;
    }

    @RequestMapping("add")
    public String add(@RequestBody User user) {
        return userService.add(user);
    }
}
