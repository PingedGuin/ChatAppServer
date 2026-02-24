package dev.dre.chatappserver.apis.register;

import dev.dre.chatappserver.ChatAppServerApplication;
import dev.dre.chatappserver.dtos.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public class LoginController {
    @GetMapping("/api/auth/login")
    public ResponseEntity<?> login(@Valid @RequestHeader("Authorization") String token, @RequestBody LoginRequest request) {
        if (ChatAppServerApplication.getDatabase().isUserExist(request.getUsername())){
            return ResponseEntity.ok().body("logged in");
        }
        return ResponseEntity.badRequest().body("user not found");
    }
}
