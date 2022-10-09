package com.management.InventoryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO {
    private Integer authorityId;
    private String roleId;
    private Integer userId;
//    private String userName;
}
