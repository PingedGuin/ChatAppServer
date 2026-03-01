package dev.dre.chatappserver;

import dev.dre.chatappserver.database.UserInfo;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import dev.dre.chatappserver.security.auth.service.TokenService;

@SpringBootApplication
public class ChatAppServerApplication {
    @Getter
    private static final UserInfo database = new UserInfo();
    @Getter
    private static final TokenService tokenService = new TokenService();

    public static void main(String[] args) {
        SpringApplication.run(ChatAppServerApplication.class, args);
    }

}
