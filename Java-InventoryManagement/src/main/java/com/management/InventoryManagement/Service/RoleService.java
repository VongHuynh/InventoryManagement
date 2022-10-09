package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.RoleDTO;
import lombok.Data;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getRoleList();
    RoleDTO updateRole(RoleDTO role);
}
