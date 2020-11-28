package com.liming.mapper.user;

import com.liming.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserEntity queryByUserName(@Param("username") String username);
    UserEntity queryByUsernamePassword(UserEntity userEntity);
}
