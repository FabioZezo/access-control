package com.git.zezo.access_control.config;

import com.git.zezo.access_control.domain.security.ClientIdentification;
import com.git.zezo.access_control.domain.security.CustomAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordMasterAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login = authentication.getName();
        var password = (String) authentication.getCredentials();

        String loginMaster = "master";
        String passwordMaster = "54321";

        if (loginMaster.equals(login) && passwordMaster.equals(password)) {

            ClientIdentification clientIdentification = new ClientIdentification(
                    "I'm a Master",
                    "MASTER",
                    loginMaster,
                    List.of("ADMIN"));

            return new CustomAuthentication(clientIdentification);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
