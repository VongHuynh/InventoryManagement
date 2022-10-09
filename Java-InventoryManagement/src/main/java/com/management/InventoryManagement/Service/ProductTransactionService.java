package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Payload.Response.ProductTransResponse;

import java.util.List;

public interface ProductTransactionService {
    ProductTransactionDTO insertProductTransaction(ProductTransactionDTO productTransaction);
    ProductTransactionDTO updateStatusTrans(Integer id);
    List<LogProductTransactionDTO> findLogByTransId(Integer id);
    List<ProductTransResponse> findTransByStatusId(Integer id);
    List<ProductTransResponse> findTransByProductId(Integer id);
}
