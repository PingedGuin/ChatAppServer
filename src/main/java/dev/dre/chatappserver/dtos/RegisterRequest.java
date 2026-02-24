package dev.dre.chatappserver.dtos;

import jakarta.validation.constraints.NotBlank;

@NotBlank
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
