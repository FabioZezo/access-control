package com.git.zezo.access_control.domain.repository;

import com.git.zezo.access_control.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByLogin(String login);
}
