package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.DTO.AuthorityDTO;
import com.management.InventoryManagement.Service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authorities")
@CrossOrigin("*")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @GetMapping@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.ok(authorityService.findAllAuthorities());
    }

    @PostMapping @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> insertAuthority(AuthorityDTO authorityDTO){
        return ResponseEntity.ok(authorityService.insertAuthority(authorityDTO));
    }

    @PutMapping @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateAuthority(AuthorityDTO authorityDTO){
        return ResponseEntity.ok(authorityService.updateAuthority(authorityDTO));
    }
}
