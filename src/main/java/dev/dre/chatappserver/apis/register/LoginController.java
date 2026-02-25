package dev.dre.chatappserver.apis.register;

import dev.dre.chatappserver.ChatAppServerApplication;
import dev.dre.chatappserver.dtos.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println(request);
        if (ChatAppServerApplication.getDatabase().isUserExist(request.getUsername())){
            return ResponseEntity.ok().body("logged in");
        }
        return ResponseEntity.badRequest().body("user not found");
    }
}
