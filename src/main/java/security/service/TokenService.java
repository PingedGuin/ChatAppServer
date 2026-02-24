package security.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public boolean validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }

        return token.equals("token");
    }
    //todo
    // - JWT verification
    // - DB lookup
    // - Expiration check
}
