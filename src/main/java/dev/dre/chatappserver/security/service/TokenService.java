package dev.dre.chatappserver.security.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public boolean validateToken(String token) {
        if (token.isBlank() || !token.startsWith("Bearer ")) {
            return false;
        }

        return token.equals("token");
    }
    public String generateToken(String username) {
        return "token";
    }
    //todo
    // - JWT verification
    // - DB lookup
    // - Expiration check
}
