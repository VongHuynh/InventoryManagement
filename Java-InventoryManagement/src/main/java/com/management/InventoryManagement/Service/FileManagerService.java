package com.management.InventoryManagement.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileManagerService {
    List<String> save(MultipartFile[] files);

    byte[] read(String folder, String filename);
    void delete(String filename);
}
