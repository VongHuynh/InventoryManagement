package com.management.InventoryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTransactionDTO {
    private Integer productTransID;
    private Integer amount;
//    private Integer statusId;
    private String statusName;
    private Integer productID;
}
