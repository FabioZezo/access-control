package com.git.zezo.access_control.api.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RhController {

    @GetMapping("/technical")
    @PreAuthorize("hasAnyRole('RH_TECHNICAL','RH_MANAGER','ADMIN')")
    public ResponseEntity<String> technical(){
        return ResponseEntity.ok("Technical route");
    }
    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('RH_MANAGER','ADMIN')")
    public ResponseEntity<String> manager(){
        return ResponseEntity.ok("Manager route");
    }
}
