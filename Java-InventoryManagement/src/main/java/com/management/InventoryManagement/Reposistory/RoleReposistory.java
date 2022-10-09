package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleReposistory extends JpaRepository<Role, String> {

}
