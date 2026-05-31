package com.app.register.security.auth;

import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import com.app.register.dtos.register.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final PasswordEncoderService passwordEncoderService;
    UserInfoRepository userInfoRepository;

    RegisterService(UserInfoRepository userInfoRepository, PasswordEncoderService passwordEncoderService) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    public boolean handleRegister(RegisterRequest request) {

        if (request == null || request.IsEmpty()) return false;

        if (userInfoRepository.existsByEmail(request.getEmail())) return false;
        if (userInfoRepository.existsByUsername(request.getUsername())) return false;

        String encodedPassword = passwordEncoderService.encode(request.getPassword());

        UserInfoEntity user = UserInfoEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(encodedPassword)
                .build();

        userInfoRepository.save(user);

        return true;
    }
}
