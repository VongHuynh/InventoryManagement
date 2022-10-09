package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.ProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTransactionReposistory extends JpaRepository<ProductTransaction, Integer> {
    List<ProductTransaction> findAllByProductIDProductId(Integer productID);
    List<ProductTransaction> findAllByStatusStatusID(Integer statusID);
}
