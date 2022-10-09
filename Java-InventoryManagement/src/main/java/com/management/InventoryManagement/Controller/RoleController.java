package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.DTO.RoleDTO;
import com.management.InventoryManagement.Payload.Response.ObjectResponse;
import com.management.InventoryManagement.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public ResponseEntity<?> getAllRole(){
        List<RoleDTO> list = roleService.getRoleList();
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(), "Successfully", true, list));
    }

    @PutMapping
    public ResponseEntity<?> updateRole(@RequestBody RoleDTO role){
        RoleDTO roleDTO = roleService.updateRole(role);
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(), "Successfully",true,
                Collections.singletonList(roleDTO)));
    }
}
