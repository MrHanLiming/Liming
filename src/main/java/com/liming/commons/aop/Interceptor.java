package com.liming.commons.aop;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.liming.commons.exception.exceptionhandler.resultbody.ResultBody;
import com.liming.commons.interfacea.AuthLogin;
import com.liming.config.LimingConfig;
import com.liming.utils.CookieUtil;
import com.liming.utils.TokenUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器  用于在请求开始前拦截
 * 1、拦截ip
 * @author Liming.Han
 */
public class Interceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Integer.class);

    @Autowired
    private LimingConfig limingConfig;

    /*请求之前执行，返回true才进行后续操作*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //是否开启ip校验
        if (limingConfig.isNeedCheckIp()){
            String userAddress = request.getRemoteAddr();
            String[] ipList = limingConfig.getWhiteRoster().split(",");
            if (!this.checkIp(ipList,userAddress)){
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                LOGGER.error("拦截ip:  "+userAddress);
                return false;
            }
        }
        //校验登录
        if (!this.checkLogin(handler,request)){
            response.setStatus(HttpStatus.SC_OK);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(ResultBody.LoginError().toJsonString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        return true;
    }

    /*请求结束执行*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    /*视图渲染结束执行*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {
    }

    //验证ip
    private boolean checkIp(String[] ipList,String userAddress){
        if (ipList != null){
            for (String address : ipList){
                if (address.equals(userAddress)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
       校验是否已经登录
     */
    private boolean checkLogin(Object handler,HttpServletRequest request){
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //拦截所有的请求方法也就是方法上的注解，如果needLogin为false及返回true
            AuthLogin authLoginMethod = handlerMethod.getMethod().getAnnotation(AuthLogin.class);
            if(authLoginMethod != null && !authLoginMethod.needLogin()){
                return true;
            }
            //拦截整个模块也就是所有类上的注解，同理
            AuthLogin authLoginClass = handlerMethod.getBeanType().getAnnotation(AuthLogin.class);
            if(authLoginClass != null && !authLoginClass.needLogin()){
                return true;
            }
            //进行认证
            try {
                String token = request.getHeader("token");
                if (!StringUtils.isEmpty(token)){
                    //根据token尝试获取userId不抛异常即为有效
                    TokenUtil.getUserId(token);
                    return true;
                }
            }catch (TokenExpiredException tokenExpiredException){
                return false;
            }
            //认证结束
        }
        return false;
    }
}
