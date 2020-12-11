package com.liming.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liming.commons.interfacea.AuthLogin;
import com.liming.commons.resultformat.Result;
import com.liming.entity.user.UserEntity;
import com.liming.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/userLogin")
    @AuthLogin(needLogin = false)
    public Result<UserEntity> userLogin(@RequestBody UserEntity userEntity) throws Exception {
        if (userEntity == null)
            return Result.error("用户信息不能为空");
        Result userInfo = userService.userLogin(userEntity);
        return userInfo;
    }
    //获取用户权限菜单--返回格式为前端路由器所需
    @GetMapping("/getUserMenu/{userId}")
    public Result getUserMenu(@PathVariable int userId){
        if (userId == 0)
            return Result.error("参数错误!");
        return userService.getUserMenu(userId);
    }

    //获取用户信息通过userId
    @GetMapping("/getUserInfoByUserId/{userId}")
    public Result<UserEntity> getUserInfoByUserId(@PathVariable("userId")Integer userId){
        if (userId == 0)
            return Result.error("参数错误!");
        return userService.getUserInfoByUserId(userId);
    }

}
