package ma.easy_apps.boycott.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.input.CreateProductInput;
import ma.easy_apps.boycott.dto.input.SearchProductsInput;
import ma.easy_apps.boycott.dto.response.PagedProductResponse;
import ma.easy_apps.boycott.dto.response.ProductResponse;
import ma.easy_apps.boycott.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {

    private final ProductService productService;

    /**
     * Search products with various filters
     * POST /api/products/search
     */
    @PostMapping("/search")
    public ResponseEntity<PagedProductResponse> searchProducts(@Valid @RequestBody SearchProductsInput input) {
        log.info("Searching products with input: {}", input);
        PagedProductResponse response = productService.searchProducts(input);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all products with pagination
     * GET /api/products?page=0&size=10&sortBy=createdAt&sortDirection=DESC
     */
    @GetMapping
    public ResponseEntity<PagedProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ) {
        log.info("Getting all products - page: {}, size: {}", page, size);
        PagedProductResponse response = productService.getAllProducts(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(response);
    }

    /**
     * Get products by category name
     * GET /api/products/category/{categoryName}
     */
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String categoryName) {
        log.info("Getting products by category: {}", categoryName);
        List<ProductResponse> products = productService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    /**
     * Get product by ID
     * GET /api/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        log.info("Getting product by ID: {}", id);
        Optional<ProductResponse> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get product by QR code
     * GET /api/products/qr/{qrCode}
     */
    @GetMapping("/qr/{qrCode}")
    public ResponseEntity<ProductResponse> getProductByQrCode(@PathVariable String qrCode) {
        log.info("Getting product by QR code: {}", qrCode);
        Optional<ProductResponse> product = productService.getProductByQrCode(qrCode);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new product
     * POST /api/products
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductInput input) {
        log.info("Creating product: {}", input);
        ProductResponse response = productService.createProduct(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    /**
     * Create product with image upload
     */
    @PostMapping(value = "/products-with-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProductWithImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("qrCode") String qrCode,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("isBoycott") Boolean isBoycott,
            @RequestParam(value = "isActive", defaultValue = "true") Boolean isActive
    ) {
        try {
            CreateProductInput input = new CreateProductInput();
            input.setName(name);
            input.setQrCode(qrCode);
            input.setCategoryName(categoryName);
            input.setIsBoycott(isBoycott);
            input.setIsActive(isActive);

            ProductResponse product = productService.createProductWithImage(input, file);
            log.info("Product created successfully with image: {}", product.getId());
            return ResponseEntity.ok(product);
        } catch (IOException e) {
            log.error("Error creating product with image: ", e);
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to create product: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Create product with image upload
     * POST /api/products/with-image
     */
    //@PostMapping(value = "/with-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> createProductWithImage(
            @Valid @ModelAttribute CreateProductInput input,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        log.info("Creating product with image: {}", input);
        ProductResponse response = productService.createProductWithImage(input, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Update an existing product
     * PUT /api/products/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody CreateProductInput input
    ) {
        log.info("Updating product with ID: {}", id);
        ProductResponse response = productService.updateProduct(id, input);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a product (soft delete)
     * DELETE /api/products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        log.info("Deleting product with ID: {}", id);
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
