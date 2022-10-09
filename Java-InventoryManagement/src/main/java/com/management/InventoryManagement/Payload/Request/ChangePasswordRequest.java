package com.management.InventoryManagement.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank(message = "username is mandatory")
    private String username;
    @Length(min = 6, max = 20, message = "Password is mandatory and length must be between 6 and 20 characters")
    private String oldPassword;
    @Length(min = 6, max = 20, message = "Password is mandatory and length must be between 6 and 20 characters")
    private String newPassword;
}
