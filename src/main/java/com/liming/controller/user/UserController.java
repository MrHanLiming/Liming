package com.liming.controller.user;

import com.liming.commons.interfacea.AuthLogin;
import com.liming.commons.resultformat.Result;
import com.liming.config.ConstantConfig;
import com.liming.entity.user.UserEntity;
import com.liming.service.user.UserService;
import com.liming.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/userLogin")
    @AuthLogin(needLogin = false)
    public Result<UserEntity> userLogin(@RequestBody UserEntity userEntity, HttpServletResponse response){
        if (userEntity == null)
            return Result.error("用户信息不能为空");
        Result<UserEntity> userInfo = userService.userLogin(userEntity);
        if (userInfo.getRetCode().equals("1"))
            response.addCookie(CookieUtil.setValue(ConstantConfig.COOKIE_A_TOKEN,userInfo.getData().getToken()));
        return userInfo;
    }
    //获取用户权限菜单--返回格式为前端路由器所需
    @GetMapping("/getUserMenu/{userId}")
    public Result getUserMenu(@PathVariable int userId){
        return userService.getUserMenu(userId);
    }

    //获取用户信息通过userId
    @GetMapping("/getUserInfoByUserId/{userId}")
    public Result<UserEntity> getUserInfoByUserId(@PathVariable("userId")Integer userId){
        return userService.getUserInfoByUserId(userId);
    }

    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserEntity userEntity){
        if (userEntity == null)
            return Result.error("参数有误!");
        return userService.updateUserInfo(userEntity);
    }

    //修改密码
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map param,HttpServletResponse response){
        Result result = userService.updatePassword(param);
        //修改完密码清除cookie重新获取token
        if("1".equals(result.getRetCode())){
            response.addCookie(CookieUtil.removeCookie());
        }
        return result;
    }

    //登出
    @GetMapping("/loginOut")
    public Result loginOut(HttpServletResponse response){
        response.addCookie(CookieUtil.removeCookie());
        return Result.success("登出成功");
    }

}
