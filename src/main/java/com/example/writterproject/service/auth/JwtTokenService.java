package com.example.writterproject.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.writterproject.dto.auth.AuthRequest;
import com.example.writterproject.dto.auth.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Service

public class JwtTokenService {

    @Value("${jwt.lifetime}")
    private Duration jwtLiveTime;

    private final Algorithm HMAC256;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") String jwtSecret) {
        this.HMAC256 = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(HMAC256).build();
    }

    public AuthResponse jwtGenerate(AuthRequest request) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String token = JWT.create()
                .withSubject(request.getUserName())
                .withIssuedAt(Instant.from(localDateTime))
                .withExpiresAt(Instant.now().plusMillis(jwtLiveTime.toMillis()))
                .sign(HMAC256);
        return new AuthResponse(token);
    }


    public String checkUserNameFromJwt(String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Wrong token " + token);
        }
    }


}
