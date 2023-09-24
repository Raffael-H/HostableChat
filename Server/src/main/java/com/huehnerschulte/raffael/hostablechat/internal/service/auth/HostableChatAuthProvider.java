package com.huehnerschulte.raffael.hostablechat.internal.service.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class HostableChatAuthProvider implements AuthenticationProvider {

    private final HostableChatUserDetailsService userDetailsService;

    public HostableChatAuthProvider(HostableChatUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !password.equals(userDetails.getPassword())){
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return new HostableChatAuthToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(HostableChatAuthToken.class);
    }
}
