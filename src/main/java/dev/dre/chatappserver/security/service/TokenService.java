package dev.dre.chatappserver.security.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public boolean validateToken(String token) {
        if (token.isBlank() || !token.startsWith("Bearer ")) {
            return false;
        }
        String actualToken = token.substring(7);

        return actualToken.equals("token");
    }
    //todo
    // - JWT verification
    // - DB lookup
    // - Expiration check
}
