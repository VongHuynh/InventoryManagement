package com.management.InventoryManagement.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String username;
    private Integer resultCode;
    private String message;
    private Boolean isSuccess;
}
