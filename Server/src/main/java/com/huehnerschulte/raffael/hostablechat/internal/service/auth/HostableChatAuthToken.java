package com.huehnerschulte.raffael.hostablechat.internal.service.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class HostableChatAuthToken extends AbstractAuthenticationToken {
    private final UserDetails principal;
    private final String credentials;

    public HostableChatAuthToken(UserDetails principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
