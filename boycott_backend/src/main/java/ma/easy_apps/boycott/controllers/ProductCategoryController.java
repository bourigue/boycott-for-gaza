package ma.easy_apps.boycott.controllers;

import lombok.RequiredArgsConstructor;
import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.services.ProductCategoryService;
import ma.easy_apps.boycott.utils.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    /**
     * Create a new product category
     */
    @PostMapping
    public ResponseEntity<GenericResponse<ProductCategory>> createProductCategory(
            @RequestParam String name,
            @RequestParam(required = false) String description) {
        GenericResponse<ProductCategory> response = productCategoryService.createProductCategory(name, description);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get all product categories
     */
    @GetMapping
    public ResponseEntity<GenericResponse<List<ProductCategory>>> getAllProductCategories() {
        GenericResponse<List<ProductCategory>> response = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok(response);
    }

    /**
     * Get product category by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProductCategory>> getProductCategoryById(@PathVariable String id) {
        GenericResponse<ProductCategory> response = productCategoryService.getProductCategoryById(id);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get product category by name
     */
    @GetMapping("/by-name")
    public ResponseEntity<GenericResponse<ProductCategory>> getProductCategoryByName(@RequestParam String name) {
        GenericResponse<ProductCategory> response = productCategoryService.getProductCategoryByName(name);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update product category
     */
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ProductCategory>> updateProductCategory(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam(required = false) String description) {
        GenericResponse<ProductCategory> response = productCategoryService.updateProductCategory(id, name, description);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
