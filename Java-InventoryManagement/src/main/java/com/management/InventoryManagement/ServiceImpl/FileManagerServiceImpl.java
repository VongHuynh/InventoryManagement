package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.Service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileManagerServiceImpl implements FileManagerService {
    @Autowired
    ServletContext app;
    private Path getPath( String filename) {
        File dir = Paths.get(app.getRealPath("/files"), "uploads").toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("path: " + dir.getAbsolutePath());
        return Paths.get(dir.getAbsolutePath(), filename);
    }

    @Override
    public List<String> save(MultipartFile[] files) {
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file : files) {
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = this.getPath(filename);
            try {
                file.transferTo(path);
                filenames.add(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filenames;
    }

    @Override
    public byte[] read(String folder, String filename) {
        return new byte[0];
    }

    @Override
    public void delete(String filename) {

    }
}
