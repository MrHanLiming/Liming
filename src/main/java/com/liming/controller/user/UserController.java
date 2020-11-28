package com.liming.controller.user;

import com.liming.commons.resultformat.Result;
import com.liming.entity.user.UserEntity;
import com.liming.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/userLogin")
    public Result<UserEntity> userLogin(@RequestBody UserEntity userEntity) throws Exception {
        if (userEntity == null)
            return Result.error("用户信息不能为空");
        Result userInfo = userService.userLogin(userEntity);
        return userInfo;
    }

}
