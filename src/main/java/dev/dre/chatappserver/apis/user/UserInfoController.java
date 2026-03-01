package dev.dre.chatappserver.apis.user;

import dev.dre.chatappserver.ChatAppServerApplication;
import dev.dre.chatappserver.security.service.AuthService.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @GetMapping("/api/user/@me/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value = "Authorization") String token) {
        var tokenService = ChatAppServerApplication.getTokenService();
        var userId = tokenService.extractUserId(token);

        return null;
    }
}