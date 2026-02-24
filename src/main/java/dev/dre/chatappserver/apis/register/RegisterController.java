package dev.dre.chatappserver.apis.register;

import dev.dre.chatappserver.dtos.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestHeader("Authorization") String token, @RequestBody RegisterRequest request) {
        if (!token.equals("token")) {
            return ResponseEntity.badRequest()
                    .body("Invalid token");
        }
        return ResponseEntity.ok().build();
    }
}
