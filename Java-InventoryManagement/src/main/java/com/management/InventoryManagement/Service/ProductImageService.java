package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.ProductImageDTO;
import com.management.InventoryManagement.Entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    ProductImageDTO insertProductImage(ProductImageDTO productImageDTO);
    void updateProductImage(List<Integer> ids, Integer productID);
}
