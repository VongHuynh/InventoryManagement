package com.management.InventoryManagement.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTransResponse {
    private Integer productTransID;
    private Integer amount;
    private String statusName;
    private String productName;
}
