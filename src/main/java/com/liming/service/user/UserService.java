package com.liming.service.user;

import com.liming.commons.resultformat.Result;
import com.liming.entity.user.UserEntity;

import java.util.Map;

public interface UserService {
    UserEntity queryByUserName(String username);
    Result<UserEntity> userLogin(UserEntity userEntity);
    Result getUserMenu(int userId);
    Result getUserInfoByUserId(Integer userId);
    Result updateUserInfo(UserEntity userEntity);
    Result updatePassword(Map param);
}
