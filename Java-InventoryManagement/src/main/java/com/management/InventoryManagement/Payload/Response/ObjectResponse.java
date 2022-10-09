package com.management.InventoryManagement.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse {
    private Integer httpStatus;
    private String message;
    private Boolean isSuccess;
    private List<?> data;
}
