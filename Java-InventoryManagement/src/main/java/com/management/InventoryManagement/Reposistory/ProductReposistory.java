package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReposistory extends JpaRepository<Product, Integer> {
    List<Product> findAllByIsDeleted(Boolean isDeleted);
}
