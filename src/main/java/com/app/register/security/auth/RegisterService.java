package com.app.register.security.auth;

import com.app.user.data.entity.UserEntity;
import com.app.user.repository.UserRepository;
import com.app.register.dtos.register.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final PasswordEncoderService passwordEncoderService;
    UserRepository userRepository;

    RegisterService(UserRepository userRepository, PasswordEncoderService passwordEncoderService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    public boolean handleRegister(RegisterRequest request) {

        if (request == null || request.IsEmpty()) return false;

        if (userRepository.existsByEmail(request.getEmail())) return false;
        if (userRepository.existsByUsername(request.getUsername())) return false;

        String encodedPassword = passwordEncoderService.encode(request.getPassword());

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(encodedPassword)
                .build();

        userRepository.save(user);

        return true;
    }
}
