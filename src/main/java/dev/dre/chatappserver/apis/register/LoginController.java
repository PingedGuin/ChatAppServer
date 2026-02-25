package dev.dre.chatappserver.apis.register;

import dev.dre.chatappserver.ChatAppServerApplication;
import dev.dre.chatappserver.dtos.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request , HttpServletResponse response) {
        System.out.println(request);
        if (ChatAppServerApplication.getDatabase().isUserExist(request.getUsername())){
            String token = ChatAppServerApplication.getTokenService().generateToken(request.getUsername());

            Cookie cookie = new Cookie("jwt", token);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);

            response.addCookie(cookie);
            return ResponseEntity.ok().body("logged in");

        }

        return ResponseEntity.badRequest().body("user not found");
    }
}
