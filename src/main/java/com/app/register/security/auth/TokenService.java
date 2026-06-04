package com.app.register.security.auth;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.app.register.security.user.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Service
public class TokenService {
    private final String SECRET = "super-long-random-secret-key-256-bits"; // todo change this

    public String generateAccessToken(String userId, String sessionId, String role, String expireAt) {
        //todo add role for the user in include it in the token
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(expireAt);
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(userId)
                    .claim("sessionId", sessionId)
                  //  .claim("role", role)  //todo add this later
                    .expirationTime(date)
                    .issueTime(new Date())
                    .build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            SignedJWT jwt = new SignedJWT(header, claims);
            JWSSigner signer = new MACSigner(SECRET);

            jwt.sign(signer);

            return jwt.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            return jwt.verify(new MACVerifier(SECRET)) &&
                    new Date().before(jwt.getJWTClaimsSet().getExpirationTime());
        } catch (Exception e) {
            log.error("Invalid token", e);
            return false;
        }
    }
    public long extractUserId(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
//            String role = tokenService.extractRole(token);
            return Long.parseLong(jwt.getJWTClaimsSet().getSubject());
        } catch (Exception e) {
            log.error("Failed to extract userId", e);
            return 0;
        }
    }
    public Boolean isTokenExpired(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            Date expiration = jwt.getJWTClaimsSet().getExpirationTime();
            return new Date().after(expiration);
        } catch (Exception e) {
            log.error("Error checking token expiration", e);
            return true;
        }
    }

    public UserPrincipal loadUserById(String userId) {
        return null;
    }

    //todo
    // - JWT verification
    // - DB lookup
    // - Expiration check
}
