package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityReposistory extends JpaRepository<Authority, Integer> {
}
