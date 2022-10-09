package com.management.InventoryManagement.DTO;

import com.management.InventoryManagement.Entity.ProductTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogProductTransactionDTO {
    private Integer LogProductTransID;
    private Date dateLog = new Date();
    private Integer productTransID;
    private Integer userId;
}
