package com.liming.service.user;

import com.liming.entity.user.UserEntity;

public interface UserService {
    UserEntity queryByUserName(String username);
}
