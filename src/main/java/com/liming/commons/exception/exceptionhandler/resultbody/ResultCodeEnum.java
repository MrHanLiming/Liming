package com.liming.commons.exception.exceptionhandler.resultbody;

public enum ResultCodeEnum {

    //内部异常
    HTTPINTERIOR("500","服务器内部错误"),
    NOTACCESS("403","禁止访问"),
    NOTFIND("404","未找到"),
    HTTPIERROR("400","错误的请求");

    private String retCode;
    private String retMsg;

    ResultCodeEnum (String retCode,String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
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
}
