package com.liming.service.user.impl;

import com.liming.entity.user.UserEntity;
import com.liming.mapper.user.UserMapper;
import com.liming.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity queryByUserName(String username) {
        UserEntity userInfo = userMapper.queryByUserName(username);
        return userInfo;
    }
}
