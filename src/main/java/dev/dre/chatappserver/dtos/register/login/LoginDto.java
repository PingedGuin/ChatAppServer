package dev.dre.chatappserver.dtos.register.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.boot.web.server.Cookie;

@NotBlank
@Getter
public class LoginDto {
    String username;
    String password;
    Cookie cookie;
}
