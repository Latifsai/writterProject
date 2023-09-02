package com.example.writterproject.configurations;

import com.example.writterproject.domain.User;
import com.example.writterproject.service.UserService;
import com.example.writterproject.service.auth.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Manager;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private static final String BARRIER_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith(BARRIER_PREFIX)) {
            prepareUserName(header, request);
            filterChain.doFilter(request, response);
        }
    }

    private void prepareUserName(String header, HttpServletRequest request) {
        String jwtToken = header.substring(BARRIER_PREFIX.length());
        String userName = jwtTokenService.checkUserNameFromJwt(jwtToken);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findUserByName(userName);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            Stream.of(user.getRole())
                                    .map(element -> new SimpleGrantedAuthority(element.getName()))
                                    .toList()
                    );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

}
