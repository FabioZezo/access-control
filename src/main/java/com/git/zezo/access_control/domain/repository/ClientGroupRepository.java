package com.git.zezo.access_control.domain.repository;

import com.git.zezo.access_control.domain.entity.Client;
import com.git.zezo.access_control.domain.entity.ClientGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientGroupRepository extends JpaRepository<ClientGroup, String> {

    @Query("""
            
            select distinct tg.name
            from ClientGroup cg
            join cg.typeGroup tg
            join cg.client c
            where c = ?1
            
    """)
    List<String> findPermissionsByClient(Client client);
}
