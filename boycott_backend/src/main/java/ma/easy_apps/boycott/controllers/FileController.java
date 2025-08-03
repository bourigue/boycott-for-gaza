package ma.easy_apps.boycott.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.input.CreateProductInput;
import ma.easy_apps.boycott.dto.response.ProductResponse;
import ma.easy_apps.boycott.services.FileStorageService;
import ma.easy_apps.boycott.services.ProductService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileStorageService fileStorageService;
    private final ProductService productService;

    /**
     * Upload a single file
     */
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileStorageService.storeFile(file);
            Map<String, String> response = new HashMap<>();
            response.put("fileUrl", fileUrl);
            response.put("message", "File uploaded successfully");
            
            log.info("File uploaded successfully: {}", fileUrl);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.error("Error uploading file: ", e);
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to upload file: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }



    /**
     * Serve uploaded files
     */
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get("uploads").resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() || resource.isReadable()) {
                // Try to determine file's content type
                String contentType = "application/octet-stream";
                if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
                    contentType = "image/jpeg";
                } else if (filename.toLowerCase().endsWith(".png")) {
                    contentType = "image/png";
                } else if (filename.toLowerCase().endsWith(".gif")) {
                    contentType = "image/gif";
                } else if (filename.toLowerCase().endsWith(".webp")) {
                    contentType = "image/webp";
                }
                
                return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            log.error("Error serving file: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete uploaded file
     */
    @DeleteMapping("/uploads/{filename:.+}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable String filename) {
        try {
            String fileUrl = "/uploads/" + filename;
            boolean deleted = fileStorageService.deleteFile(fileUrl);
            
            Map<String, String> response = new HashMap<>();
            if (deleted) {
                response.put("message", "File deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "File not found or could not be deleted");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error deleting file: ", e);
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to delete file: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
