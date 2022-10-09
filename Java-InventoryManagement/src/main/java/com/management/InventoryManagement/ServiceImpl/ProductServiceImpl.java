package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.DTO.ProductDTO;
import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Entity.LogProductTransaction;
import com.management.InventoryManagement.Entity.Product;
import com.management.InventoryManagement.Entity.ProductTransaction;
import com.management.InventoryManagement.Reposistory.ProductReposistory;
import com.management.InventoryManagement.Service.ProductService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductReposistory productReposistory;
    @Autowired
    private Convert convert;

    @Override
    public ProductDTO insertProduct(ProductDTO productDTO) {
        Product product = convert.toEntity(productDTO, Product.class);
        return convert.toDto(productReposistory.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO updateAmountProduct(ProductDTO productDTO) {
        Product product = productReposistory.findById(productDTO.getProductId()).orElse(null);
        product.setAmount(product.getAmount() + productDTO.getAmount());
        return convert.toDto(productReposistory.save(product), ProductDTO.class);
    }

    @Override
    public void deleteProduct(Integer id) {
        productReposistory.deleteById(id);
    }

    @Override
    public List<ProductDTO> findProductIsDeleted() {
        List<Product> products = productReposistory.findAllByIsDeleted(true);
        List<ProductDTO> productsDTO = products.stream().map(product -> convert.toDto(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productsDTO;
    }

    @Override
    public List<ProductDTO> findProductIsNoDeleted() {
        List<Product> products = productReposistory.findAllByIsDeleted(false);
        List<ProductDTO> productsDTO = products.stream().map(product -> convert.toDto(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productsDTO;
    }

    @Override
    public List<ProductTransactionDTO> findProductTransById(Integer id) {
        Optional<Product> product = productReposistory.findById(id);
        List<ProductTransactionDTO> listTransDTO = product.get().getProductTransactions().stream()
                .map(transaction -> convert.toDto(transaction, ProductTransactionDTO.class)).collect(Collectors.toList());
        return listTransDTO;
    }
}
