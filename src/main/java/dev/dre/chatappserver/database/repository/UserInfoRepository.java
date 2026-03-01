package dev.dre.chatappserver.database.repository;

import dev.dre.chatappserver.dtos.register.login.LoginDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoRepository {

    public void save() {

    }

    public boolean isUserExist(LoginDto request) {
        return false;
    }
}
