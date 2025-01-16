package com.git.zezo.access_control.domain.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClientIdentification {
    private String id;
    private String name;
    private String login;
    private List<String> permissions;

    public ClientIdentification(String id, String name, String login, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        return permissions;
    }
}
