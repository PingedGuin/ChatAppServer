package dev.dre.chatappserver;

import dev.dre.chatappserver.apis.config.WebConfig;
import dev.dre.chatappserver.database.UserInfo;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatAppServerApplication {
    @Getter
    private static final UserInfo database = new UserInfo();

    public static void main(String[] args) {
        SpringApplication.run(ChatAppServerApplication.class, args);
    }

}
