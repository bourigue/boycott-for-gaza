package ma.easy_apps.boycott.controllers;

import lombok.RequiredArgsConstructor;
import ma.easy_apps.boycott.dto.CreateBoycottProductRequestDto;
import ma.easy_apps.boycott.dto.ProductSearchResultDto;
import ma.easy_apps.boycott.entities.BoycottProduct;
import ma.easy_apps.boycott.services.BoycottProductService;
import ma.easy_apps.boycott.utils.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boycott-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoycottProductController {

    private final BoycottProductService boycottProductService;

    /**
     * Search for a product by QR code
     * Returns boycott product with alternatives, alternative product, or not found
     */
    @GetMapping("/search")
    public ResponseEntity<GenericResponse<ProductSearchResultDto>> searchProductByQrCode(
            @RequestParam String qrCode) {
        GenericResponse<ProductSearchResultDto> response = boycottProductService.searchProductByQrCode(qrCode);
        return ResponseEntity.ok(response);
    }

    /**
     * Create a new boycott product with alternatives
     */
    @PostMapping
    public ResponseEntity<GenericResponse<BoycottProduct>> createBoycottProduct(
            @RequestBody CreateBoycottProductRequestDto request) {
        GenericResponse<BoycottProduct> response = boycottProductService.createBoycottProduct(request);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get all active boycott products
     */
    @GetMapping
    public ResponseEntity<GenericResponse<List<BoycottProduct>>> getAllActiveBoycottProducts() {
        GenericResponse<List<BoycottProduct>> response = boycottProductService.getAllActiveBoycottProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Get boycott product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<BoycottProduct>> getBoycottProductById(@PathVariable String id) {
        GenericResponse<BoycottProduct> response = boycottProductService.getBoycottProductById(id);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update boycott product status
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<GenericResponse<BoycottProduct>> updateBoycottProductStatus(
            @PathVariable String id, 
            @RequestParam boolean isActive) {
        GenericResponse<BoycottProduct> response = boycottProductService.updateBoycottProductStatus(id, isActive);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
