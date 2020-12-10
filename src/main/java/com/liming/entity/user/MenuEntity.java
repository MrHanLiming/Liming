package com.liming.entity.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MenuEntity {

    private int menuId;
    private String menuTitle;
    private String menuLevel;
    private int parentUuid;
    private String menuIcon;
    private String menuPath;
    private Timestamp creatTime;
    private int creatUserId;
    private String creatNickName;
    private String menuComponent;

}
