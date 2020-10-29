package com.liming.entity.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserEntity {

    private int userId;
    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private String nickName;
    private Timestamp creatTime;
    private String creatUserId;
    private String CreatNickName;

}
