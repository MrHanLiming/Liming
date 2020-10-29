package com.liming.commons.exception.exceptionhandler;

import com.liming.commons.exception.exceptionhandler.resultbody.ResultBody;
import com.liming.commons.exception.exceptiontype.ArgumentIllegalException;
import com.liming.commons.exception.exceptiontype.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    //非法参数异常
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ArgumentIllegalException.class)
    public ResultBody exceptionHandler(ArgumentIllegalException e){
        log.error("非法参数异常！",e);
        return ResultBody.notAccess(e.getMessage());
    }

    //业务异常
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpClientException.class)
    public ResultBody exceptionHandler(HttpClientException e){
        log.error("业务异常！",e);
        return ResultBody.httpInterior(e.getMessage());
    }

    // 处理空指针的异常
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value =NullPointerException.class)
    public ResultBody exceptionHandler(NullPointerException e){
        log.error("空指针异常！",e);
        return ResultBody.httpInterior(e.getMessage());
    }

    //处理未知异常
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value =  Exception.class)
    public ResultBody exceptionHandler(Exception e){
        log.error("未知异常！",e);
        return ResultBody.httpInterior(e.getMessage());
    }

}
