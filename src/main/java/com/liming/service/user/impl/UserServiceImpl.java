package com.liming.service.user.impl;

import com.alibaba.fastjson.JSONArray;
import com.liming.commons.resultformat.Result;
import com.liming.entity.user.MenuEntity;
import com.liming.entity.user.ResultMenuEntity;
import com.liming.entity.user.UserEntity;
import com.liming.mapper.user.UserMapper;
import com.liming.service.user.UserService;
import com.liming.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //根据username查询用户信息
    @Override
    public UserEntity queryByUserName(String username) {
        if (StringUtils.isEmpty(username))
            return null;
        UserEntity userInfo = userMapper.queryByUserName(username);
        return userInfo;
    }

    //用户登录
    @Override
    public Result userLogin(UserEntity userEntity) {
        String username = userEntity.getUsername();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(userEntity.getPassword()))
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

    //查询菜单
    @Override
    public Result getUserMenu(int userId) {
        if (userId == 0){
            return Result.error("参数错误!");
        }
        List<MenuEntity> menuList = userMapper.queryMenuByUserId(userId);
        List<ResultMenuEntity> data = menuFormat(menuList);
        return Result.success("",data);
    }

    //格式化菜单 树形结构
    private List<ResultMenuEntity> menuFormat(List<MenuEntity> menuList){
        List<ResultMenuEntity> resultMenuList = null;
        if (menuList != null || menuList.size() > 0){
            resultMenuList = new ArrayList<>();
            Map<Integer,ResultMenuEntity> nodeList = new HashMap<>();
            for (MenuEntity menuEntity : menuList){
                ResultMenuEntity resultMenuEntity = new ResultMenuEntity();
                resultMenuEntity.setPath(menuEntity.getMenuPath());
                resultMenuEntity.setComponent(menuEntity.getMenuComponent());
                resultMenuEntity.setIcon(menuEntity.getMenuIcon());
                resultMenuEntity.setName(menuEntity.getMenuTitle());
                resultMenuEntity.setParentId(menuEntity.getParentUuid());
                nodeList.put(menuEntity.getMenuId(),resultMenuEntity);
            }
            for (MenuEntity menuEntity : menuList){
                ResultMenuEntity resultMenuEntity = nodeList.get(menuEntity.getMenuId());
                if (resultMenuEntity.getParentId() == 0){
                    resultMenuList.add(resultMenuEntity);
                }else {
                    nodeList.get(menuEntity.getParentUuid()).setChildren(new ArrayList());
                    nodeList.get(menuEntity.getParentUuid()).getChildren().add(resultMenuEntity);
                }
            }
        }
        return resultMenuList;
    }

}
