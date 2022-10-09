package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.AuthorityDTO;
import com.management.InventoryManagement.Entity.Authority;
import com.management.InventoryManagement.Entity.Role;
import com.management.InventoryManagement.Entity.UserAccount;
import com.management.InventoryManagement.Reposistory.AuthorityReposistory;
import com.management.InventoryManagement.Service.AuthorityService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityReposistory authorityReposistory;
    @Autowired
    private Convert convert;

    @Override
    public AuthorityDTO insertAuthority(AuthorityDTO authorityDTO) {
//        Authority authority = convert.toEntity(authorityDTO, Authority.class);
        Authority authority = new Authority();
        authority.setRole(new Role(authorityDTO.getRoleId()));
        authority.setUserAccount(new UserAccount(authorityDTO.getUserId()));
        return convert.toDto(authorityReposistory.save(authority), AuthorityDTO.class);
    }

    @Override
    public AuthorityDTO updateAuthority(AuthorityDTO authorityDTO) {
        Optional<Authority> oldAuthority = authorityReposistory.findById(authorityDTO.getAuthorityId());
        if (oldAuthority.isPresent()) {
            oldAuthority.get().setRole(new Role(authorityDTO.getRoleId()));
            return convert.toDto(authorityReposistory.save(oldAuthority.get()), AuthorityDTO.class);
        }
        return null;
    }

    @Override
    public void deleteAuthority(Integer authorityID) {

    }

    @Override
    public List<AuthorityDTO> findAllAuthorities() {
        List<Authority> authorities = authorityReposistory.findAll();
        List<AuthorityDTO> authorityDTOS = authorities.stream().map(au -> convert.toDto(au, AuthorityDTO.class)).collect(Collectors.toList());
        return authorityDTOS;
    }
}
