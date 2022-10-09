package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.LogProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogProductTransactionReposistory extends JpaRepository<LogProductTransaction, Integer> {
}
