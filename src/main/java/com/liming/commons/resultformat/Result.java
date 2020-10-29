package com.liming.commons.resultformat;


import com.alibaba.fastjson.JSONObject;

public class Result<T> {

    private String retCode;
    private String retMsg;
    private boolean isSuccess;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Result(){

    };

    private Result(String retCode,boolean isSuccess){
        this.retCode = retCode;
        this.isSuccess = isSuccess;
    };

    private Result(String retCode,boolean isSuccess, String retMsg){
        this.retCode = retCode;
        this.isSuccess = isSuccess;
        this.retMsg = retMsg;
    };

    private Result(String retCode,boolean isSuccess, String retMsg, T data){
        this.retCode = retCode;
        this.isSuccess = isSuccess;
        this.retMsg = retMsg;
        this.data = data;
    };

    //成功
    public static Result success() {
        CommonEnum commonEnum = CommonEnum.SUCCESS;
        return new Result(commonEnum.getRetCode(),true,commonEnum.getRetMsg());
    };

    public static Result success(String retMsg) {
        CommonEnum commonEnum = CommonEnum.SUCCESS;
        return new Result(commonEnum.getRetCode(),true,retMsg);
    };

    public static <T> Result<T> success(String retMsg,T data) {
        return new Result(CommonEnum.SUCCESS.getRetCode(),true,retMsg,data);
    };

    //失败
    public static <T> Result<T> error(String retMsg,T data) {
        return new Result(CommonEnum.SUCCESS.getRetCode(),true,retMsg,data);
    };

    public static Result error() {
        CommonEnum commonEnum = CommonEnum.ERROR;
        return new Result(commonEnum.getRetCode(),false,commonEnum.getRetMsg());
    };

    public static Result error(String retMsg) {
        return new Result(CommonEnum.ERROR.getRetCode(),false,retMsg);
    };

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    public enum CommonEnum{
        // 数据操作错误定义
        SUCCESS("1", "成功!"),
        ERROR("0", "请求失败!");

        //错误码
        private String retCode;
        //描述
        private String retMsg;

        CommonEnum(String retCode,String retMsg){
            this.retCode = retCode;
            this.retMsg = retMsg;
        }

        public String getRetCode(){
            return this.retCode;
        }

        public String getRetMsg(){
            return this.retMsg;
        }
    }
}
