package ma.easy_apps.boycott.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageService {

    @Value("${app.file.upload-dir:uploads}")
    private String uploadDir;

    @Value("${app.file.max-size:5242880}") // 5MB default
    private long maxFileSize;

    private static final List<String> ALLOWED_IMAGE_EXTENSIONS = Arrays.asList(
        "jpg", "jpeg", "png", "gif", "bmp", "webp"
    );

    /**
     * Store uploaded file and return the file URL
     */
    public String storeFile(MultipartFile file) throws IOException {
        validateFile(file);
        
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);
        
        // Generate unique filename
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String uniqueFilename = String.format("%s_%s.%s", 
            UUID.randomUUID().toString().substring(0, 8),
            timestamp,
            extension
        );
        
        // Store file
        Path targetLocation = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        
        log.info("Successfully stored file: {}", uniqueFilename);
        return uniqueFilename;
    }

    /**
     * Delete file from storage
     */
    public boolean deleteFile(String fileUrl) {
        try {
            if (fileUrl == null || !fileUrl.startsWith("/uploads/")) {
                return false;
            }
            
            String filename = fileUrl.substring("/uploads/".length());
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("Successfully deleted file: {}", filename);
                return true;
            }
            
            return false;
        } catch (IOException e) {
            log.error("Error deleting file: {}", fileUrl, e);
            return false;
        }
    }

    /**
     * Check if file exists
     */
    public boolean fileExists(String fileUrl) {
        if (fileUrl == null || !fileUrl.startsWith("/uploads/")) {
            return false;
        }
        
        String filename = fileUrl.substring("/uploads/".length());
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        return Files.exists(filePath);
    }

    /**
     * Get file size
     */
    public long getFileSize(String fileUrl) throws IOException {
        if (fileUrl == null || !fileUrl.startsWith("/uploads/")) {
            return 0;
        }
        
        String filename = fileUrl.substring("/uploads/".length());
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        
        if (Files.exists(filePath)) {
            return Files.size(filePath);
        }
        
        return 0;
    }

    /**
     * Validate uploaded file
     */
    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException(
                String.format("File size exceeds maximum allowed size of %d MB", maxFileSize / (1024 * 1024))
            );
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("File name is null");
        }

        String extension = FilenameUtils.getExtension(originalFilename).toLowerCase();
        if (!ALLOWED_IMAGE_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException(
                String.format("File type not allowed. Allowed types: %s", 
                    String.join(", ", ALLOWED_IMAGE_EXTENSIONS))
            );
        }
    }
}
