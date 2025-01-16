package com.git.zezo.access_control.domain.repository;

import com.git.zezo.access_control.domain.entity.TypeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<TypeGroup, String> {
    Optional<TypeGroup> findByName(String name);
}
