package com.zev.application.service;

import com.zev.application.common.Constants;
import com.zev.application.dto.request.AuthRequest;
import com.zev.application.dto.request.RefreshTokenRequest;
import com.zev.application.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
           String accessToken = jwtService.generateAccessToken(authRequest.getUsername());
           String refreshToken = jwtService.generateRefreshToken(authRequest.getUsername());
           String timeExpired = jwtService.genTimeExpiredJwt();
           return LoginResponse.builder()
                   .accessToken(accessToken)
                   .refreshToken(refreshToken)
                   .timeExpired(timeExpired)
                   .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        return new LoginResponse();
    }
}
