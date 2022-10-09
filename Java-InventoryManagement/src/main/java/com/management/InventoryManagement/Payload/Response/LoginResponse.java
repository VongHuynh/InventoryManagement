package com.management.InventoryManagement.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private final String type="Bearer";
    private Integer userId;
    private String username;
    private Integer resultCode;
    private String message;
    private boolean isSuccess;
    private List<String> roles;

}
