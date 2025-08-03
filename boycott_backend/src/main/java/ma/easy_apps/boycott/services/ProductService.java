package ma.easy_apps.boycott.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.input.CreateProductInput;
import ma.easy_apps.boycott.dto.input.SearchProductsInput;
import ma.easy_apps.boycott.dto.response.PagedProductResponse;
import ma.easy_apps.boycott.dto.response.ProductResponse;
import ma.easy_apps.boycott.entities.Product;
import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService categoryService;
    private final FileStorageService fileStorageService;

    /**
     * Search products with various filters
     */
    public PagedProductResponse searchProducts(SearchProductsInput input) {
        Sort sort = createSort(input.getSortBy(), input.getSortDirection());
        Pageable pageable = PageRequest.of(input.getPage(), input.getSize(), sort);
        
        Page<Product> productPage;
        
        if (input.getCategoryName() != null && !input.getCategoryName().trim().isEmpty()) {
            // Search by category
            productPage = productRepository.findByCategoryName(input.getCategoryName(), pageable);
        } else if (input.getSearchTerm() != null && !input.getSearchTerm().trim().isEmpty()) {
            // Search by term
            productPage = productRepository.searchProducts(input.getSearchTerm(), pageable);
        } else if (input.getIsBoycott() != null) {
            // Filter by boycott status
            productPage = productRepository.findByIsBoycottAndIsActiveTrueOrderByCreatedAtDesc(
                input.getIsBoycott(), pageable);
        } else {
            // Get all active products
            productPage = productRepository.findByIsActiveTrueOrderByCreatedAtDesc(pageable);
        }
        
        List<ProductResponse> productResponses = productPage.getContent()
            .stream()
            .map(ProductResponse::fromEntity)
            .collect(Collectors.toList());
        
        return new PagedProductResponse(
            productResponses,
            productPage.getNumber(),
            productPage.getSize(),
            productPage.getTotalElements(),
            productPage.getTotalPages(),
            productPage.isFirst(),
            productPage.isLast(),
            productPage.isEmpty()
        );
    }

    /**
     * Get products by category name
     */
    public List<ProductResponse> getProductsByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryNameIgnoreCaseAndIsActiveTrue(categoryName);
        return products.stream()
            .map(ProductResponse::fromEntity)
            .collect(Collectors.toList());
    }

    /**
     * Get product by ID
     */
    public Optional<ProductResponse> getProductById(String id) {
        return productRepository.findById(id)
            .filter(Product::isActive)
            .map(ProductResponse::fromEntity);
    }

    /**
     * Get product by QR code
     */
    public Optional<ProductResponse> getProductByQrCode(String qrCode) {
        return productRepository.findByQrCodeAndIsActiveTrue(qrCode)
            .map(ProductResponse::fromEntity);
    }

    /**
     * Create a new product
     */
    @Transactional
    public ProductResponse createProduct(CreateProductInput input) {
        log.info("Creating product with name: {}", input.getName());
        
        // Validate QR code uniqueness
        if (productRepository.existsByQrCodeAndIsActiveTrue(input.getQrCode())) {
            throw new IllegalArgumentException("Product with this QR code already exists");
        }
        
        // Get or create category
        ProductCategory category = categoryService.getOrCreateCategory(input.getCategoryName());
        
        // Create product entity
        Product product = new Product();
        product.setName(input.getName());
        product.setQrCode(input.getQrCode());
        product.setBoycott(input.getIsBoycott());
        product.setActive(input.getIsActive() != null ? input.getIsActive() : true);
        product.setImageUrl(input.getImageUrl());
        product.setCategory(category);
        
        Product savedProduct = productRepository.save(product);
        log.info("Successfully created product with ID: {}", savedProduct.getId());
        
        return ProductResponse.fromEntity(savedProduct);
    }

    /**
     * Create product with image upload
     */
    @Transactional
    public ProductResponse createProductWithImage(CreateProductInput input, MultipartFile imageFile) throws IOException {
        String imageUrl = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = fileStorageService.storeFile(imageFile);
            input.setImageUrl(imageUrl);
        }
        
        try {
            return createProduct(input);
        } catch (Exception e) {
            // If product creation fails, clean up uploaded image
            if (imageUrl != null) {
                fileStorageService.deleteFile(imageUrl);
            }
            throw e;
        }
    }

    /**
     * Update product
     */
    @Transactional
    public ProductResponse updateProduct(String id, CreateProductInput input) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        
        // Validate QR code uniqueness (exclude current product)
        if (!product.getQrCode().equals(input.getQrCode()) && 
            productRepository.existsByQrCodeAndIsActiveTrue(input.getQrCode())) {
            throw new IllegalArgumentException("Product with this QR code already exists");
        }
        
        // Get or create category
        ProductCategory category = categoryService.getOrCreateCategory(input.getCategoryName());
        
        // Update product
        product.setName(input.getName());
        product.setQrCode(input.getQrCode());
        product.setBoycott(input.getIsBoycott());
        product.setActive(input.getIsActive() != null ? input.getIsActive() : product.isActive());
        if (input.getImageUrl() != null) {
            product.setImageUrl(input.getImageUrl());
        }
        product.setCategory(category);
        
        Product savedProduct = productRepository.save(product);
        log.info("Successfully updated product with ID: {}", savedProduct.getId());
        
        return ProductResponse.fromEntity(savedProduct);
    }

    /**
     * Delete product (soft delete)
     */
    @Transactional
    public boolean deleteProduct(String id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setActive(false);
            productRepository.save(product);
            log.info("Successfully deleted product with ID: {}", id);
            return true;
        }
        return false;
    }

    /**
     * Get all products with pagination
     */
    public PagedProductResponse getAllProducts(int page, int size, String sortBy, String sortDirection) {
        Sort sort = createSort(sortBy, sortDirection);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByIsActiveTrueOrderByCreatedAtDesc(pageable);
        
        List<ProductResponse> productResponses = productPage.getContent()
            .stream()
            .map(ProductResponse::fromEntity)
            .collect(Collectors.toList());
        
        return new PagedProductResponse(
            productResponses,
            productPage.getNumber(),
            productPage.getSize(),
            productPage.getTotalElements(),
            productPage.getTotalPages(),
            productPage.isFirst(),
            productPage.isLast(),
            productPage.isEmpty()
        );
    }

    private Sort createSort(String sortBy, String sortDirection) {
        Sort.Direction direction = "ASC".equalsIgnoreCase(sortDirection) 
            ? Sort.Direction.ASC 
            : Sort.Direction.DESC;
        return Sort.by(direction, sortBy);
    }
}
