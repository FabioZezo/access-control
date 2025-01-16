package com.git.zezo.access_control.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final ClientIdentification clientIdentification;

    public CustomAuthentication(ClientIdentification clientIdentification) {

        if (clientIdentification == null){
            throw new ExceptionInInitializerError("It is not possible to create CustomAuthentication without user identification");
        }
        this.clientIdentification = clientIdentification;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.clientIdentification
                .getPermissions()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.clientIdentification;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("No need to call, we are already authenticated");
    }

    @Override
    public String getName() {
        return this.clientIdentification.getName();
    }
}
