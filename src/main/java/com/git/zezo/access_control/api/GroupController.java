package com.git.zezo.access_control.api;

import com.git.zezo.access_control.domain.entity.TypeGroup;
import com.git.zezo.access_control.domain.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typegroups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository repository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TypeGroup> save(@RequestBody TypeGroup typeGroup){
        repository.save(typeGroup);
        return ResponseEntity.ok(typeGroup);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TypeGroup>> list(){
        return ResponseEntity.ok(repository.findAll());
    }
}

