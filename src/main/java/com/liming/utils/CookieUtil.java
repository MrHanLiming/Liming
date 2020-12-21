package com.liming.utils;

import com.alibaba.fastjson.JSON;
import com.liming.config.ConstantConfig;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie setValue(String key,Object value){
        String v = "";
        if (value != null){
            v = JSON.toJSON(value).toString();
        }
        Cookie cookie = new Cookie(key,v);
        cookie.setPath("/");
        //过期时间 10天
        cookie.setMaxAge(60*60*10);
        return cookie;
    }

    //获取Token
    public static String getToken(Cookie[] cookies){
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(ConstantConfig.COOKIE_A_TOKEN)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
