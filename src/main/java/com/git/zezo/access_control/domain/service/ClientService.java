package com.git.zezo.access_control.domain.service;

import com.git.zezo.access_control.domain.entity.Client;
import com.git.zezo.access_control.domain.entity.ClientGroup;
import com.git.zezo.access_control.domain.entity.TypeGroup;
import com.git.zezo.access_control.domain.repository.ClientGroupRepository;
import com.git.zezo.access_control.domain.repository.ClientRepository;
import com.git.zezo.access_control.domain.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final GroupRepository groupRepository;
    private final ClientGroupRepository clientGroupRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client save(Client client, List<String> typeGroups) {
        String encrypetedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encrypetedPassword);
        clientRepository.save(client);

        List<ClientGroup> listClientGroup = typeGroups.stream().map(nameGroup -> {
                    Optional<TypeGroup> possibleGroup = groupRepository.findByName(nameGroup);
                    if (possibleGroup.isPresent()) {
                        TypeGroup typeGroup = possibleGroup.get();
                        return new ClientGroup(client, typeGroup);
                    }
                    return null;
                })
                .filter(typeGroup -> typeGroup != null)
                .collect(Collectors.toList());

        clientGroupRepository.saveAll(listClientGroup);

        return client;
    }

    public Client getClientWithPermissions(String login) {
        Optional<Client> clientOptional = clientRepository.findByLogin(login);
        if (clientOptional.isEmpty()) {
            return null;
        }

        Client client = clientOptional.get();
        List<String> permissions = clientGroupRepository.findPermissionsByClient(client);
        client.setPermissions(permissions);

        return client;

    }
}
