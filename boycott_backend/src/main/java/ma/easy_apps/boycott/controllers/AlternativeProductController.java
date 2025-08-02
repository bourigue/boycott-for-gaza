package ma.easy_apps.boycott.controllers;

import lombok.RequiredArgsConstructor;
import ma.easy_apps.boycott.dto.CreateAlternativeProductDto;
import ma.easy_apps.boycott.entities.AlternativeProduct;
import ma.easy_apps.boycott.services.AlternativeProductService;
import ma.easy_apps.boycott.utils.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alternative-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlternativeProductController {

    private final AlternativeProductService alternativeProductService;

    /**
     * Create a new alternative product
     */
    @PostMapping
    public ResponseEntity<GenericResponse<AlternativeProduct>> createAlternativeProduct(
            @RequestBody CreateAlternativeProductDto request,
            @RequestParam String boycottProductId) {
        GenericResponse<AlternativeProduct> response = alternativeProductService.createAlternativeProduct(request, boycottProductId);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get all active alternative products
     */
    @GetMapping
    public ResponseEntity<GenericResponse<List<AlternativeProduct>>> getAllActiveAlternativeProducts() {
        GenericResponse<List<AlternativeProduct>> response = alternativeProductService.getAllActiveAlternativeProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Get alternative products by boycott product ID
     */
    @GetMapping("/by-boycott-product/{boycottProductId}")
    public ResponseEntity<GenericResponse<List<AlternativeProduct>>> getAlternativesByBoycottProductId(
            @PathVariable String boycottProductId) {
        GenericResponse<List<AlternativeProduct>> response = alternativeProductService.getAlternativesByBoycottProductId(boycottProductId);
        return ResponseEntity.ok(response);
    }

    /**
     * Get alternative product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<AlternativeProduct>> getAlternativeProductById(@PathVariable String id) {
        GenericResponse<AlternativeProduct> response = alternativeProductService.getAlternativeProductById(id);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update alternative product status
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<GenericResponse<AlternativeProduct>> updateAlternativeProductStatus(
            @PathVariable String id,
            @RequestParam boolean isActive) {
        GenericResponse<AlternativeProduct> response = alternativeProductService.updateAlternativeProductStatus(id, isActive);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
