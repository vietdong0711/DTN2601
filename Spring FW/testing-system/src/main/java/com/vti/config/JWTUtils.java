package com.vti.config;

import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")// lay gtri tu file .properties
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username) {// tạo ra token từ username
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {// lấy username từ token
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public boolean checkExpired(String token) {
        Date exp = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getExpiration();
        return exp.before(new Date());
    }
}
