package dev.dre.chatappserver.dtos.register.login;

import jakarta.servlet.http.Cookie;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;
    private String username;
    private long expiresIn;
    private Cookie cookie;
    public LoginResponseDto(String token, String username,Cookie cookie ,long expiresIn) {
        this.token = token;
        this.username = username;
        this.cookie = cookie;
        this.expiresIn = expiresIn;
    }
}