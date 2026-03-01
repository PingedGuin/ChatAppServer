package dev.dre.chatappserver.apis.register;

import dev.dre.chatappserver.ChatAppServerApplication;
import dev.dre.chatappserver.dtos.register.LoginRequest;
import dev.dre.chatappserver.security.service.AuthService.LoginService;
import dev.dre.chatappserver.security.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;
    private final TokenService tokenService;

    public LoginController(LoginService loginService, TokenService tokenService) {
        this.loginService = loginService;
        this.tokenService = tokenService;
    }
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request , HttpServletResponse response) {

        if (ChatAppServerApplication.getDatabase().isUserExist(request.getUsername())){ // change it
            String token = tokenService.generateToken(request.getUsername());

            Cookie cookie = new Cookie("jwt", token);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);

            response.addCookie(cookie);
            return ResponseEntity.ok().body("logged in");

        }

        return ResponseEntity.badRequest().body("user not found");
    }
}
