package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.UserModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {


    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间
     */
    private static final long EXPIRATION = 7200L; //  单位为秒

    /**
     * 生成用户token，设置token超时时间
     */
    public static String createToken(UserModel userModel) {
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = null;
        token = JWT.create()
                .withHeader(map) // 添加头部
                // 可以将基本信息放到claims中
                .withClaim("userId", userModel.getId()) // userId
                .withClaim("userName", userModel.getUsername())
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date()) // 签发时间
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // 解码异常则抛出异常
            return null;
        }
        return jwt.getClaims();
    }


}