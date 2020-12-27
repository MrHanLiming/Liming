package com.liming.utils;

import com.alibaba.fastjson.JSON;
import com.liming.config.ConstantConfig;

import javax.servlet.http.Cookie;

public class CookieUtil {

    private final static int COOKIE_EXPIRE_TIME = 60*60*10;

  /**
     * 创建cookie
     * setMaxAge()   该方法用于设置cookie的生存时间，传入的参数表示生存时间，是int型的--秒数值
     * 设置负数的秒值，等同于--添加cookie时没有调用该方法，则浏览器默认将cookie保存在内存里，当浏览器关闭时，cookie从内存中释放(没有了)。
     * 这种设置用于删除同名的cookie，在servlet中创建一个与已存在的cookie同名的cookie，设置该cookie生存时间为0，将该cookie添加进去，
     * 将覆盖原cookie，但因该cookie生存时间为0，所以会马上消失，起到了删除特定cookie的作用。
     *
     * setPath()  设置cookie路径
     *
    */
    public static Cookie setValue(String key,Object value){
        String v = "";
        if (value != null){
            v = JSON.toJSON(value).toString();
        }
        Cookie cookie = new Cookie(key,v);
        cookie.setPath("/");
        cookie.setDomain("yunwaigame.top");
        cookie.setMaxAge(COOKIE_EXPIRE_TIME);
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

    /**
     * 删除cookie
    */
    public static Cookie removeCookie(){
        Cookie newCookie = new Cookie(ConstantConfig.COOKIE_A_TOKEN,null);
        newCookie.setMaxAge(0);
        newCookie.setPath("/");
        newCookie.setDomain("yunwaigame.top");
        return newCookie;
    }
}
