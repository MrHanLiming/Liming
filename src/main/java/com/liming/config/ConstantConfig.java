package com.liming.config;

public class ConstantConfig {
    /**
     * _A可用于扩展业务  增加业务在次注明
     * A cookis存放key
     * B 存放字段名前缀
    */
    //cookie前缀
    public final static String COOKIE_PRIFX_A = "COOKIE_A%s";
    public final static String COOKIE_A_TOKEN = String.format(COOKIE_PRIFX_A, "TOKEN");

    //字段名前缀
    public final static String COOKIE_PRIFX_B = "COOKIE_B%s";
    //当前登录用户 - 用户名
    public final static String COOKIE_B_USERNAME = String.format(COOKIE_PRIFX_B, "USERNAME");
    //当前登录用户 - 用户id
    public final static String COOKIE_B_USERID = String.format(COOKIE_PRIFX_B, "USERID");
    //当前登录用户 - 用户手机号
    public final static String COOKIE_B_PHONENUM = String.format(COOKIE_PRIFX_B, "PHONENUM");
    //当前登录用户 - 用户邮件
    public final static String COOKIE_B_EMAIL = String.format(COOKIE_PRIFX_B, "EMAIL");
    //当前登录用户 - 用户昵称
    public final static String COOKIE_B_NICKNAME = String.format(COOKIE_PRIFX_B, "NICKNAME");

    /** token秘钥，请勿泄露，请勿随便修改 */
    public final static String TOKEN_SECRET = "H9m69Ly5J";

}
