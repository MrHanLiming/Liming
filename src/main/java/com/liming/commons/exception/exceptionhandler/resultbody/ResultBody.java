package com.liming.commons.exception.exceptionhandler.resultbody;

import com.alibaba.fastjson.JSONObject;

public class ResultBody {
    private String retCode;
    private String retMsg;
    private boolean isSuccess;

    private ResultBody(String retCode, String retMsg,boolean isSuccess) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.isSuccess = isSuccess;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    //服务器内部错误
    public static ResultBody httpInterior(){
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.HTTPINTERIOR;
        return new ResultBody(resultCodeEnum.getRetCode(),resultCodeEnum.getRetMsg(),false);
    }
    public static ResultBody httpInterior(String retMsg){
        return new ResultBody(ResultCodeEnum.HTTPINTERIOR.getRetCode(),retMsg,false);
    }

    //禁止访问
    public static ResultBody notAccess(){
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.NOTACCESS;
        return new ResultBody(resultCodeEnum.getRetCode(),resultCodeEnum.getRetMsg(),false);
    }
    public static ResultBody notAccess(String retMsg){
        return new ResultBody(ResultCodeEnum.NOTACCESS.getRetCode(),retMsg,false);
    }

    //未找到
    public static ResultBody notFind(){
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.NOTFIND;
        return new ResultBody(resultCodeEnum.getRetCode(),resultCodeEnum.getRetMsg(),false);
    }

    //错误的请求
    public static ResultBody httpIerror(){
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.HTTPIERROR;
        return new ResultBody(resultCodeEnum.getRetCode(),resultCodeEnum.getRetMsg(),false);
    }

    //未登录认证
    public static ResultBody LoginError(){
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.LOGINERROR;
        return new ResultBody(resultCodeEnum.getRetCode(),resultCodeEnum.getRetMsg(),false);
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
