package dev.dre.chatappserver.apis.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @GetMapping("/api/user/@me/info")
    public void getUserInfo(@RequestHeader(value = "Authorization") String token) {
    }
}