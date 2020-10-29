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

    @PostMapping("/userLogin")
    public Result<UserEntity> userLogin(@RequestBody UserEntity userEntity){
        return Result.success("",userService.queryByUserName(userEntity.getUsername()));
    }

}
