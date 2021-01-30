package com.wytest.springboot.controller;

import com.wytest.springboot.bean.User;
import com.wytest.springboot.mapper.UserMapper;
import com.wytest.springboot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @RequestMapping("qryUser")
    public Object qryUser(String id){
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

    @RequestMapping("updUser")
    public String updUser(User user){
        logger.debug("开始更新用户信息！");
        userMapper.updUser(user);
        redisUtil.set(user.getId(), user);
        logger.debug("updUser.end");
        return "更新成功！";
    }

    @RequestMapping("delUser")
    public String delUser(String id){
        int i = userMapper.delUser(id);
        redisUtil.del(id);
        logger.debug("删除缓存");
        return "删除成功！";
    }

    @RequestMapping("insertUser")
    public String insertUser(User user){
        userMapper.insertUser(user);
        redisUtil.set(user.getId(),user);
        logger.debug("新增成功！");
        return "新增成功";
    }
}
