package com.wytest.springboot.mapper;

import com.wytest.springboot.bean.User;

/**
 * TODO
 * @author weiyu
 * @date 2021/1/27
 */

public interface UserMapper {

    User selectById(String id);

}
