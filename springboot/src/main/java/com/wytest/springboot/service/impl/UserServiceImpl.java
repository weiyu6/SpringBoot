package com.wytest.springboot.service.impl;

import com.wytest.springboot.bean.User;
import com.wytest.springboot.controller.UserController;
import com.wytest.springboot.mapper.UserMapper;
import com.wytest.springboot.service.UserService;
import com.wytest.springboot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author weiyu
 * @date 2021/4/13
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public Object qryUser(User u) {
        logger.debug("user:{}",u.toString());
        String id = u.getId();
        boolean hasKey = redisUtil.hasKey(id);
        User user = new User();
        if(hasKey){
            logger.debug("存在缓存");
            user = (User) redisUtil.get(id);
        }else {
            logger.debug("缓存不存在！");
            user =userMapper.selectById(id);
            if(user == null){
                return "无结果！";
            }
            redisUtil.set(id, user);
        }
        return user;
    }

    @Override
    public String add(User user) {
        String id = user.getId();
        userMapper.insertUser(user);

        User user1 = userMapper.selectById(id);
        logger.debug("查询结果：{}",user1.toString());
        String name = user1.getName();
        if(id.equals("20")){
            if(name.equals("张三")){
                throw new RuntimeException("出错了") ;
            }
        }
        return "成功！";
    }
}
