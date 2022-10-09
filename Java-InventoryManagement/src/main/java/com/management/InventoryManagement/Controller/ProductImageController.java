package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.DTO.ProductDTO;
import com.management.InventoryManagement.DTO.ProductImageDTO;
import com.management.InventoryManagement.Reposistory.ProductImageReposistory;
import com.management.InventoryManagement.Service.FileManagerService;
import com.management.InventoryManagement.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.MidiSystem;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/upload")
@CrossOrigin("*")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private FileManagerService fileManagerService;

    @PostMapping@PreAuthorize("hasAnyRole('ADMIN')")
    public List<Integer> uploadImage(@RequestParam("file")MultipartFile[] files) {
        List<Integer> ids = new ArrayList<>();
        List<String> imgURLS = fileManagerService.save(files);
        for(int i = 0; i < imgURLS.size(); i++) {
            ProductImageDTO p = productImageService.insertProductImage(new ProductImageDTO(imgURLS.get(i),null));
            ids.add(p.getImageID());
        }
        return ids;
    }

    @PutMapping("{productID}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateProductImage(@RequestBody List<Integer> list, @PathVariable Integer productID){
        productImageService.updateProductImage(list, productID);
        return ResponseEntity.ok().build();
    }

}
