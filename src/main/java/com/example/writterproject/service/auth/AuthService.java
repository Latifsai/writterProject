package com.example.writterproject.service.auth;

import com.example.writterproject.dto.auth.AuthRequest;
import com.example.writterproject.dto.auth.AuthResponse;
import com.example.writterproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse createToken(AuthRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect login/password!");
        }

        return jwtTokenService.jwtGenerate(request);
    }
}
