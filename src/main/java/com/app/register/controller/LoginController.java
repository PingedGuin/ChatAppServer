package com.app.register.controller;

import com.app.register.dtos.register.login.LoginRequest;
import com.app.register.security.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {

        var responseDto = authService.login(request);
        System.out.println(responseDto.getAccessToken());
        Cookie cookie = new Cookie("token", responseDto.getAccessToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        cookie.setSecure(false);

        response.addCookie(cookie);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/api/auth/me")
    public ResponseEntity<?> me(Authentication auth) {

        if (auth == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(auth.getPrincipal());
    }
}
