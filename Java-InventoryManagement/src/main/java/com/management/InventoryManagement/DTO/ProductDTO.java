package com.management.InventoryManagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.InventoryManagement.Entity.ProductImage;
import com.management.InventoryManagement.Entity.ProductTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String title;
    private String description;
    private Double price;
    private Integer amount;

    private Date importDate = new Date();
    private Integer categoryID;
//    private List<ProductImage> productImages;
//    private List<ProductTransactionDTO> productTransactions;
}
