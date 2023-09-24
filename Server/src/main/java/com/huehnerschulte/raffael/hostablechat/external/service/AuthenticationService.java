package com.huehnerschulte.raffael.hostablechat.external.service;

import com.huehnerschulte.raffael.hostablechat.external.data.representation.LoginRequest;
import com.huehnerschulte.raffael.hostablechat.internal.service.auth.HostableChatAuthManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class AuthenticationService {

    private final HostableChatAuthManager authManager;
    private final JwtTokenService tokenService;

    public AuthenticationService(HostableChatAuthManager authManager, JwtTokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public String authenticate(LoginRequest loginRequest) {
        Objects.requireNonNull(loginRequest.getUsername());
        Objects.requireNonNull(loginRequest.getPassword());
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return tokenService.generateToken(authenticate);
        } catch (AuthenticationException e) {
            log.error("Authentication failed.");
            log.debug("Error Message:",e);
            return null;
        }
    }
}
