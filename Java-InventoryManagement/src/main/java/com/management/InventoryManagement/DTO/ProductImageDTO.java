package com.management.InventoryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
    public ProductImageDTO(String imageURL, Integer productID) {
        this.imageURL = imageURL;
        this.productID = productID;
    }
    private Integer imageID;
    private String imageURL;
    private Integer productID;
}
