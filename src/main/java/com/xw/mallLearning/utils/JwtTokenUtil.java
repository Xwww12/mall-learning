package com.xw.mallLearning.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * 获取token、校验token、刷新token过期时间
 */
@Slf4j
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;  // 加密秘钥
    @Value("${jwt.expiration}")
    private Long expiration;    // token保留时间

    // 获取token中的用户名
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            return null;
        }
        return username;
    }

    // 验证token是否有效
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    // 生成Token
    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateTokenFromClaims(claims);
    }

    // 刷新token
    public String refreshToken(String token) {
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateTokenFromClaims(claims);
    }

    // 验证token是否实效
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpireDateFormToken(token);
        return expireDate.before(new Date());
    }

    // 获取Token过期时间
    private Date getExpireDateFormToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    // 根据负载信息生成Token
    private String generateTokenFromClaims(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)  // 设置token中负载信息
                .setExpiration(generateExpirationDate())    // 设置超时时间
                .signWith(SignatureAlgorithm.HS512, secret) // 设置加密方式
                .compact();
    }

    // 生成Token过期时间
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    // 获取token中的JWT负载
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT验证失败:{}", token);
        }
        return claims;
    }
}
