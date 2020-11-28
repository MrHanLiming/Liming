package com.liming.service.user.impl;

import com.liming.commons.resultformat.Result;
import com.liming.entity.user.UserEntity;
import com.liming.mapper.user.UserMapper;
import com.liming.service.user.UserService;
import com.liming.util.EncryptionUtil;
import com.qcloud.cos.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //根据username查询用户信息
    @Override
    public UserEntity queryByUserName(String username) {
        if (StringUtils.isNullOrEmpty(username))
            return null;
        UserEntity userInfo = userMapper.queryByUserName(username);
        return userInfo;
    }

    //用户登录
    @Override
    public Result userLogin(UserEntity userEntity) {
        String username = userEntity.getUsername();
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(userEntity.getPassword()))
            return Result.error("登录名或密码不能为空");
        //密码加密
        String password = EncryptionUtil.getInstance().MD5(userEntity.getPassword());
        userEntity.setPassword(password);
        //查询账号
        UserEntity userInfo = userMapper.queryByUsernamePassword(userEntity);
        if (userInfo == null)
            return Result.error("账号或密码错误");
        return Result.success("登录成功",userInfo);
    }

}
