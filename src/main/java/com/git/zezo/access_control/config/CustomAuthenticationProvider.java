package com.git.zezo.access_control.config;

import com.git.zezo.access_control.domain.entity.Client;
import com.git.zezo.access_control.domain.security.ClientIdentification;
import com.git.zezo.access_control.domain.security.CustomAuthentication;
import com.git.zezo.access_control.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = (String) authentication.getCredentials();

        Client client = clientService.getClientWithPermissions(login);

        if (client != null) {
            boolean passwordMatches = passwordEncoder.matches(password, client.getPassword());
            if (passwordMatches) {
                ClientIdentification clientIdentification = new ClientIdentification(
                        client.getId(),
                        client.getName(),
                        client.getLogin(),
                        client.getPermissions()
                );
                return new CustomAuthentication(clientIdentification);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
