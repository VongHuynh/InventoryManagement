package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageReposistory extends JpaRepository<ProductImage, Integer> {
}
