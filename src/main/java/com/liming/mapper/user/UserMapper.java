package com.liming.mapper.user;

import com.liming.entity.user.MenuEntity;
import com.liming.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity queryByUserName(@Param("username") String username);
    UserEntity queryByUsernamePassword(UserEntity userEntity);
    List<MenuEntity> queryMenuByUserId(@Param("userId") Integer userId);
    UserEntity queryByUserId(@Param("userId")Integer userId);
    void updateUserInfo(UserEntity userEntity);
}
