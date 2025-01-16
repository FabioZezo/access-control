package com.git.zezo.access_control.api.dto;

import com.git.zezo.access_control.domain.entity.Client;
import lombok.Data;

import java.util.List;

@Data
public class RegisterClientDTO {

    private Client client;
    private List<String> permissions;
}
