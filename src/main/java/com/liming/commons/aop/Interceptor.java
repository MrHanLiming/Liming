package com.liming.commons.aop;

import com.liming.config.LimingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
        if (!limingConfig.isCheckIp()){
            return true;
        }
        String userAddress = request.getRemoteAddr();
        String[] ipList = limingConfig.getWhiteRoster().split(",");
        if (ipList != null){
            for (String address : ipList){
                if (address.equals(userAddress)){
                    return true;
                }
            }
        }
        response.setStatus(404);
        LOGGER.error("拦截ip:  "+userAddress);
        return false;
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

}
