package com.todolist2.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPIRATION_TIME = 86400000;
    static final String PREFIX = "Bearer";
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final String SECRET = "n3G7u9x!vB2yH6kP1rT5sL8mQ0wZ4eR2"; // 최소 32바이트 이상
//    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());


    // 서명된 JWT 토큰 생성
    public String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 요청 Header에서 토큰을 파싱해 사용자 이름 가져오기
//    public String getAuthUser(HttpServletRequest request) {
//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (token != null) {
//            return Jwts.parser()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token.replace(PREFIX, "").trim())
//                    .getBody()
//                    .getSubject();
//        }
//        return null;
//    }
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(   // 이 클래스의 객체가 정확히 뭔지는 모르지만 method 명을 봤을 때 Header가 온다
                // 여기 Header는 postman의 Headers에 해당
                HttpHeaders.AUTHORIZATION
        );
        if (token != null) {
//            String user = Jwts.parser()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token.replace(PREFIX, ""))
//                    .getBody()
//                    .getSubject();
            String user = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX + " ", "")) // "Bearer " 전체 제거
                    .getBody()
                    .getSubject();

            if (user != null) {
                return user;
            }
        }
        return null;
    }
}
