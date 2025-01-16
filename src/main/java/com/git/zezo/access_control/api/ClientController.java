package com.git.zezo.access_control.api;

import com.git.zezo.access_control.api.dto.RegisterClientDTO;
import com.git.zezo.access_control.domain.entity.Client;
import com.git.zezo.access_control.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> save(@RequestBody RegisterClientDTO registerClientDTO){
        Client clientSave = clientService.save(registerClientDTO.getClient(),registerClientDTO.getPermissions());
        return ResponseEntity.ok(clientSave);
    }
}
