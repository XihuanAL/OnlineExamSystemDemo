package com.example.util;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {
    private long expire;
    private String secret;
    private String header;
    //生成jwt
    public String generateToken(String username){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime()+expire*1000);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(new java.util.Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    //解析jwt
    public Claims getClaimsBytoken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    //验证jwt
    public boolean isTokenExpired(Claims claims){
       return claims.getExpiration().before(new Date());
    }
}
