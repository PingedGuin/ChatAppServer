package com.app.register.security.auth;

import com.app.register.database.entitys.UserSessionEntity;
import com.app.register.dtos.register.login.LoginRequest;
import com.app.register.dtos.register.login.LoginResponseDto;
import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AuthService {
    private final UserInfoRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final TokenService tokenService;
    private final SessionService sessionService;

    AuthService(UserInfoRepository userInfoRepository, PasswordEncoderService passwordEncoderService, TokenService tokenService, SessionService sessionService) {
        this.userRepository = userInfoRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.tokenService = tokenService;
        this.sessionService = sessionService;
    }

    public LoginResponseDto login(LoginRequest request) {
        UserInfoEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoderService.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new BadCredentialsException("Invalid credentials");
        }
        UserSessionEntity session = new UserSessionEntity();
        session.setUserId(user.getId().toString());
        session.setSessionId(UUID.randomUUID().toString());

        session = sessionService.getOrCreateSession(session);

        LocalDate expireDate = LocalDate.now().plusDays(7);

        String token = tokenService.generateAccessToken(
                user.getId().toString(),
                session.getSessionId(),
                null,
                expireDate.toString()
        );

        return mapToLoginResponseDto(
                user,
                session.getSessionId(),
                expireDate.toString(),
                token
        );
    }
    private LoginResponseDto mapToLoginResponseDto(UserInfoEntity user,String sessionId,String expireDate,String accessToken) {
        return new LoginResponseDto(accessToken, user.getUsername(), expireDate,user.getId().toString(),sessionId);
    }
}
