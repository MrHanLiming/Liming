package com.liming.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.liming.config.ConstantConfig;
import com.liming.entity.user.UserEntity;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hlm
 * token相关工具类
*/
public class TokenUtil {

    /** token 过期时间: 10天 */
    public static final int CALENDARINTERVAL = 60*10;

    private static final String ALG = "HS256";
    private static final String TYP = "JWT";
    private static final String ISS = "Service";
    private static final String AUD = "master";

    /** 生成token */
    public static String createToken(UserEntity userEntity){
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, CALENDARINTERVAL);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", ALG);
        map.put("typ", TYP);
        String token = JWT.create().withHeader(map)
                .withClaim("iss", ISS)
                .withClaim("aud", AUD)
                .withClaim(ConstantConfig.COOKIE_B_USERID, userEntity.getUserId())
                .withClaim(ConstantConfig.COOKIE_B_USERNAME,userEntity.getUsername())
                .withClaim(ConstantConfig.COOKIE_B_NICKNAME,userEntity.getNickName())
                .withClaim(ConstantConfig.COOKIE_B_PHONENUM,userEntity.getPhoneNum())
                .withClaim(ConstantConfig.COOKIE_B_EMAIL,userEntity.getEmail())
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(ConstantConfig.TOKEN_SECRET));
        return token;
    }

    /** 解密token */
    private static Map<String, Claim> verifyToken(String token) throws TokenExpiredException{
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(ConstantConfig.TOKEN_SECRET)).build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            // token 校验失败, 抛出Token验证非法异常
            throw new TokenExpiredException("无效的Token");
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取userId
     * @param token
     * @return userId
     */
    public static int getUserId(String token) throws TokenExpiredException{
        Map<String, Claim> claims = verifyToken(token);
        Claim userIdClaim = claims.get(ConstantConfig.COOKIE_B_USERID);
        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asInt())) {
            // token 校验失败, 抛出Token验证非法异常
            throw new TokenExpiredException("无效的Token");
        }
        return userIdClaim.asInt();
    }

}
