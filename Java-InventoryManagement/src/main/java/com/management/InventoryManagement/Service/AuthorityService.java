package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.AuthorityDTO;

import java.util.List;

public interface AuthorityService {
    AuthorityDTO insertAuthority(AuthorityDTO authorityDTO);
    AuthorityDTO updateAuthority(AuthorityDTO authorityDTO);
    void deleteAuthority(Integer authorityID);
    List<AuthorityDTO> findAllAuthorities();
}
