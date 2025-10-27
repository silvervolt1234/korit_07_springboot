package com.example.shoppinglist.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    // 1일 (밀리초). 실제 운영시에는 더 짧은게 낫다
    static final long EXPIRATIONTIME = 86400000;
    static final String PREFIX = "Bearer";

    // 비밀 키 생성
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 서명이 이루어진 JWT 토큰을 생성
    public String getToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)      // 18번 라인의 생성한 비밀키로 서명
                .compact();
        return token;
    }

    // 요청(Request)의 Authorization 헤더에서 토큰을 가져온 뒤 그 토큰 내부를 확인하고 username을 가져오는 부분
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(   // 이 클래스의 객체가 정확히 뭔지는 모르지만 method 명을 봤을 때 Header가 온다
                // 여기 Header는 postman의 Headers에 해당
                HttpHeaders.AUTHORIZATION
        );
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return user;
            }
        }
        return null;
    }
}