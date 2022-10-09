package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.RoleDTO;
import com.management.InventoryManagement.Entity.Role;
import com.management.InventoryManagement.Reposistory.RoleReposistory;
import com.management.InventoryManagement.Service.RoleService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleReposistory roleReposistory;
    @Autowired
    private Convert convert;
    @Override
    public List<RoleDTO> getRoleList() {
        List<Role> roleList = roleReposistory.findAll();
        List<RoleDTO> roleDTOList = roleList.stream().map(role -> convert.toDto(role, RoleDTO.class)).collect(Collectors.toList());
        return roleDTOList;
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) {
        Role oldRole = roleReposistory.findById(role.getRoleID()).orElse(null);
        oldRole.setNameRole(role.getNameRole());
        oldRole = roleReposistory.save(oldRole);
        return convert.toDto(oldRole, RoleDTO.class);
    }
}
