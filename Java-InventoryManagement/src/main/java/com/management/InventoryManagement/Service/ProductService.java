package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.DTO.ProductDTO;
import com.management.InventoryManagement.DTO.ProductTransactionDTO;

import java.util.List;

public interface ProductService {
    ProductDTO insertProduct(ProductDTO product);
    ProductDTO updateAmountProduct(ProductDTO productDTO);
    void deleteProduct(Integer id);
    List<ProductDTO> findProductIsDeleted();
    List<ProductDTO> findProductIsNoDeleted();
    List<ProductTransactionDTO> findProductTransById(Integer id);
}
