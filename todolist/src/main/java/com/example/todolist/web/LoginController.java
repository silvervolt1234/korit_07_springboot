package com.example.todolist.web;

import com.example.todolist.domain.AppUser;
import com.example.todolist.dto.AccountCredentialsDto;
import com.example.todolist.service.AppUserService;
import com.example.todolist.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserService appUserService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountCredentialsDto credentials) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(), credentials.getPassword()));

            String token = jwtService.getToken(auth.getName());
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("로그인 성공");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("로그인 실패: " + e.getMessage());
        }
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AccountCredentialsDto credentials) {
        AppUser user = appUserService.registerUser(credentials.getUsername(), credentials.getPassword(), "USER");
        return ResponseEntity.ok(user);
    }
}
