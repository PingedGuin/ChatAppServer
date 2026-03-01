package dev.dre.chatappserver.security.auth.service;

import dev.dre.chatappserver.database.repository.UserInfoRepository;
import dev.dre.chatappserver.dtos.register.login.LoginDto;
import dev.dre.chatappserver.dtos.register.login.LoginResponseDto;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserInfoRepository userInfoRepository;
    private final TokenService tokenService;

    public LoginService(UserInfoRepository userInfoRepository, TokenService tokenService) {
        this.userInfoRepository = userInfoRepository;
        this.tokenService = tokenService;
    }

    public LoginResponseDto login(LoginDto request) {
        if (request == null && !userInfoRepository.isUserExist(request)) {
            return null;
        }
        String token = tokenService.generateToken(request.getUsername());
        Cookie cookie = new Cookie("jwt", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        return new LoginResponseDto(token, request.getUsername(), cookie, 60 * 60);
    }

    public void logout(String token) {

    }

    public void refreshToken() {
    }
}
