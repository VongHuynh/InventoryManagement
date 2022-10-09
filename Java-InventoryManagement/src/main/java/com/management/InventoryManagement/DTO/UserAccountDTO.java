package com.management.InventoryManagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    private Integer userID;
    @NotBlank(message = "username is mandatory")
    private String userName;
    @Length(min = 6, max = 20, message = "Password is mandatory")
    private String password;
    @NotEmpty(message = "Full name is mandatory")
    private String fullName;
    @Email(message = "Email must be in the correct format")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number incorrectly formatted")
    private String phoneNumber;
    private boolean isDeleted = Boolean.FALSE;
}
