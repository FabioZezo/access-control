package com.git.zezo.access_control.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClientGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_type_group")
    private TypeGroup typeGroup;

    public ClientGroup(Client client, TypeGroup typeGroup) {
        this.client = client;
        this.typeGroup = typeGroup;
    }
}
