package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.Entity.LogProductTransaction;
import com.management.InventoryManagement.Reposistory.LogProductTransactionReposistory;
import com.management.InventoryManagement.Service.LogProductTransactionService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogProductTransactionServiceImpl implements LogProductTransactionService {
    @Autowired
    private LogProductTransactionReposistory logProductTransactionReposistory;
    @Autowired
    private Convert convert;
    @Override
    public LogProductTransactionDTO insertLogProductTransaction(LogProductTransactionDTO logProductTransactionDTO) {
        LogProductTransaction logProductTransaction = convert.toDto(logProductTransactionDTO, LogProductTransaction.class);
        return convert.toDto(logProductTransactionReposistory.save(logProductTransaction), LogProductTransactionDTO.class);
    }
}
