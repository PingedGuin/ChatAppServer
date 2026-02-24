package dev.dre.chatappserver.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@NotBlank
@Data
public class LoginRequest {
    String username;
    String password;
}
