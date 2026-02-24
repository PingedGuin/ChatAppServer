package dev.dre.chatappserver.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@NotBlank
@Getter
public class LoginRequest {
    String username;
    String password;
}
