package dev.dre.chatappserver.apis.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @GetMapping("/api/user/@me/info")
    public void getUserInfo(@RequestHeader(value = "Authorization") String token) {
        // Token is now available in the 'token' parameter
        // If you want to extract the Bearer token value:
        if (token != null && token.startsWith("Bearer ")) {
            String actualToken = token.substring(7);
            // Use actualToken
            System.out.println(actualToken);
        }
    }
}