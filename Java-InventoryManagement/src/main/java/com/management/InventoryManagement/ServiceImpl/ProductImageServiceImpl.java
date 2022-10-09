package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.ProductImageDTO;
import com.management.InventoryManagement.Entity.Product;
import com.management.InventoryManagement.Entity.ProductImage;
import com.management.InventoryManagement.Reposistory.ProductImageReposistory;
import com.management.InventoryManagement.Service.ProductImageService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageReposistory productImageReposistory;
    @Autowired
    Convert convert;

    @Override
    public ProductImageDTO insertProductImage(ProductImageDTO productImageDTO) {
        ProductImage productImage = convert.toEntity(productImageDTO, ProductImage.class);
        return convert.toDto(productImageReposistory.save(productImage), ProductImageDTO.class);
    }

    @Override
    public void updateProductImage(List<Integer> ids, Integer productID) {
        for (int i = 0; i < ids.size(); i++) {
            Optional<ProductImage> productImage = productImageReposistory.findById(ids.get(i));
            productImage.get().setProductIMG(new Product(productID));
            productImageReposistory.save(productImage.get());
        }

    }

}
