package com.hfdy.sky.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    /**
     * 过期时间
     */
    private static final int ACCESS_EXPIRE = 60;
    /**
     * 加密算法
     */
    private static final SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;
    /**
     * 私钥
     */
    private static final String SECRET = "hfdy-sky-take-out-secret-key-test-proj";
    /**
     * 私钥实例
     */
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    /**
     * jwt签发者
     */
    private static final String JWT_ISS = "hfdy";
    /**
     * jwt主题
     */
    private static final String SUBJECT = "Peripherals";

    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return 加密结果
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 密钥实例
        final SecretKey secretKey1 = Keys.hmacShaKeyFor(secretKey.getBytes());
        // 令牌id
        String uuid = UUID.randomUUID().toString();
        Date expireDate = Date.from(Instant.now().plusSeconds(ttlMillis));
        return Jwts.builder()
                // 头部信息
                .header().add("typ", "jwt").add("alg", "HS256").and()
                // 载荷
                .claims(claims)
                // 令牌id
                .id(uuid)
                // 过期时间
                .expiration(expireDate)
                // 签发者
                .issuer(JWT_ISS)
                // 签名
                .signWith(secretKey1, ALGORITHM)
                .compact();
    }

    /**
     * 只传载荷，其他采用默认值
     *
     * @param claims 载荷
     * @return 加密结果
     */
    public static String createJWT(Map<String, Object> claims) {
        return createJWT(JwtUtil.SECRET, JwtUtil.ACCESS_EXPIRE, claims);
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        System.out.println(token);
        final SecretKey secretKey1 = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                .verifyWith(secretKey1).build().parseSignedClaims(token).getPayload();
    }

    /**
     * 不传密钥，用默认密钥
     * @param token
     * @return
     */
    public static Claims parseJWT(String token) {
        return parseJWT(SECRET, token);
    }

}
