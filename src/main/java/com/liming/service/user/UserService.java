package com.liming.service.user;

import com.liming.commons.resultformat.Result;
import com.liming.entity.user.UserEntity;

public interface UserService {
    UserEntity queryByUserName(String username);
    Result userLogin(UserEntity userEntity);
    Result getUserMenu(int userId);
    Result getUserInfoByUserId(Integer userId);
}
