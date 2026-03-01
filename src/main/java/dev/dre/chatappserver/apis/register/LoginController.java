package dev.dre.chatappserver.apis.register;

import dev.dre.chatappserver.ChatAppServerApplication;
import dev.dre.chatappserver.dtos.register.login.LoginDto;
import dev.dre.chatappserver.security.auth.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request, HttpServletResponse response) {

        var responseDto = loginService.login(request);

        if (responseDto == null) return ResponseEntity.badRequest().body("user not found");
        response.addCookie(responseDto.getCookie());
        return ResponseEntity.ok().body("logged in");

    }
}
