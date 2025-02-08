package com.elibrary.LibraLink.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileManagingService {

    @Value(value="${file.upload-dir}")
    private String upload_dir;

    public String saveFileTOServer(MultipartFile file, String userId,UUID book_UUID) throws Exception {
        try {
            // ENSURE THE DIR EXIST
            Path path = Paths.get(upload_dir);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            String originalFIleName = file.getOriginalFilename();
            assert originalFIleName != null;
            String fileExtension = originalFIleName.substring(originalFIleName.lastIndexOf("."));
            String newFileName = "user_" + userId + "_" + book_UUID + fileExtension;

            Path filePath = path.resolve(newFileName);

            // Save the file to the upload directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toString();
        } catch (IOException e) {
            throw new Exception("Error Occurred at FIle Managing Service: " +e.getMessage());
        }
    }

}
