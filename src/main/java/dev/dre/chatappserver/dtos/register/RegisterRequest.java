package dev.dre.chatappserver.dtos.register;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@NotBlank
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
