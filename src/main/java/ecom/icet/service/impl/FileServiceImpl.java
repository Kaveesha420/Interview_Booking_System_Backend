package ecom.icet.service.impl;

import ecom.icet.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    private final String uploadDir = "uploads/cvs/"; // CV save වෙන තැන

    @Override
    public String storeFile(MultipartFile file) {
        try {
            // Folder එක නැත්නම් හදන්න
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // File නම unique කරන්න (ID එකක් එකතු කරලා)
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targetPath = path.resolve(fileName);

            // File එක copy කිරීම
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath.toString(); // DB එකේ save කරන්න path එක යවනවා
        } catch (IOException e) {
            throw new RuntimeException("Could not store file. Error: " + e.getMessage());
        }
    }
}
