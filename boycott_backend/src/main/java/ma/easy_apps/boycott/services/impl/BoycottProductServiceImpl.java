package ma.easy_apps.boycott.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.*;
import ma.easy_apps.boycott.entities.AlternativeProduct;
import ma.easy_apps.boycott.entities.BoycottProduct;
import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.repos.AlternativeProductRepository;
import ma.easy_apps.boycott.repos.BoycottProductRepository;
import ma.easy_apps.boycott.repos.ProductCategoryRepository;
import ma.easy_apps.boycott.services.BoycottProductService;
import ma.easy_apps.boycott.utils.GenericResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoycottProductServiceImpl implements BoycottProductService {

    private final BoycottProductRepository boycottProductRepository;
    private final AlternativeProductRepository alternativeProductRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public GenericResponse<ProductSearchResultDto> searchProductByQrCode(String qrCode) {
        try {
            log.info("Searching for product with QR code: {}", qrCode);
            
            // First, check if it's a boycott product
            Optional<BoycottProduct> boycottProduct = boycottProductRepository.findByQrCodeAndActiveTrue(qrCode);
            if (boycottProduct.isPresent()) {
                ProductSearchResultDto result = mapToProductSearchResult(boycottProduct.get(), ProductSearchResultDto.ProductType.BOYCOTT);
                return GenericResponse.success(result, "Boycott product found with alternatives");
            }
            
            // Then, check if it's an alternative product
            Optional<AlternativeProduct> alternativeProduct = alternativeProductRepository.findByQrCodeAndActiveTrue(qrCode);
            if (alternativeProduct.isPresent()) {
                ProductSearchResultDto result = mapToProductSearchResult(alternativeProduct.get());
                return GenericResponse.success(result, "Alternative product found");
            }
            
            // Product not found
            ProductSearchResultDto result = new ProductSearchResultDto();
            result.setProductType("NOT_FOUND");
            return GenericResponse.success(result, "Product not found");
            
        } catch (Exception e) {
            log.error("Error searching for product with QR code: {}", qrCode, e);
            return GenericResponse.error("Error searching for product: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<BoycottProduct> createBoycottProduct(CreateBoycottProductRequestDto request) {
        try {
            log.info("Creating boycott product: {}", request.getName());
            
            // Validate category exists
            Optional<ProductCategory> category = productCategoryRepository.findById(request.getCategoryId());
            if (category.isEmpty()) {
                return GenericResponse.error("Product category not found");
            }
            
            // Check if QR code already exists
            Optional<BoycottProduct> existingBoycott = boycottProductRepository.findByQrCodeAndActiveTrue(request.getQrCode());
            if (existingBoycott.isPresent()) {
                return GenericResponse.error("QR code already exists for another boycott product");
            }
            
            Optional<AlternativeProduct> existingAlternative = alternativeProductRepository.findByQrCodeAndActiveTrue(request.getQrCode());
            if (existingAlternative.isPresent()) {
                return GenericResponse.error("QR code already exists for another alternative product");
            }
            
            // Create boycott product
            BoycottProduct boycottProduct = new BoycottProduct();
            boycottProduct.setName(request.getName());
            boycottProduct.setQrCode(request.getQrCode());
            boycottProduct.setImageUrl(request.getImageUrl());
            boycottProduct.setProductCategory(category.get());
            boycottProduct.setActive(true);
            
            BoycottProduct savedBoycottProduct = boycottProductRepository.save(boycottProduct);
            
            // Create alternative products if provided
            if (request.getAlternatives() != null && !request.getAlternatives().isEmpty()) {
                List<AlternativeProduct> alternatives = new ArrayList<>();
                for (CreateAlternativeProductDto altDto : request.getAlternatives()) {
                    // Check if alternative QR code already exists
                    Optional<AlternativeProduct> existingAlt = alternativeProductRepository.findByQrCodeAndActiveTrue(altDto.getQrCode());
                    if (existingAlt.isPresent()) {
                        log.warn("Alternative QR code {} already exists, skipping", altDto.getQrCode());
                        continue;
                    }
                    
                    AlternativeProduct alternative = new AlternativeProduct();
                    alternative.setName(altDto.getName());
                    alternative.setQrCode(altDto.getQrCode());
                    alternative.setImageUrl(altDto.getImageUrl());
                    alternative.setBoycottProduct(savedBoycottProduct);
                    alternative.setActive(true);
                    alternatives.add(alternative);
                }
                
                if (!alternatives.isEmpty()) {
                    alternativeProductRepository.saveAll(alternatives);
                    savedBoycottProduct.setAlternativeProducts(alternatives);
                }
            }
            
            return GenericResponse.success(savedBoycottProduct, "Boycott product created successfully");
            
        } catch (Exception e) {
            log.error("Error creating boycott product", e);
            return GenericResponse.error("Error creating boycott product: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<List<BoycottProduct>> getAllActiveBoycottProducts() {
        try {
            List<BoycottProduct> products = boycottProductRepository.findByIsActiveTrue();
            return GenericResponse.success(products, "Active boycott products retrieved successfully");
        } catch (Exception e) {
            log.error("Error retrieving active boycott products", e);
            return GenericResponse.error("Error retrieving active boycott products: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<BoycottProduct> getBoycottProductById(String id) {
        try {
            Optional<BoycottProduct> product = boycottProductRepository.findById(id);
            if (product.isPresent()) {
                return GenericResponse.success(product.get(), "Boycott product found");
            } else {
                return GenericResponse.error("Boycott product not found");
            }
        } catch (Exception e) {
            log.error("Error retrieving boycott product by id: {}", id, e);
            return GenericResponse.error("Error retrieving boycott product: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<BoycottProduct> updateBoycottProductStatus(String id, boolean isActive) {
        try {
            Optional<BoycottProduct> productOpt = boycottProductRepository.findById(id);
            if (productOpt.isEmpty()) {
                return GenericResponse.error("Boycott product not found");
            }
            
            BoycottProduct product = productOpt.get();
            product.setActive(isActive);
            product.setUpdatedAt(LocalDateTime.now());
            
            BoycottProduct savedProduct = boycottProductRepository.save(product);
            return GenericResponse.success(savedProduct, "Boycott product status updated successfully");
            
        } catch (Exception e) {
            log.error("Error updating boycott product status", e);
            return GenericResponse.error("Error updating boycott product status: " + e.getMessage());
        }
    }

    private ProductSearchResultDto mapToProductSearchResult(BoycottProduct boycottProduct, ProductSearchResultDto.ProductType type) {
        ProductSearchResultDto result = new ProductSearchResultDto();
        result.setProductType(type.name());
        result.setUuid(boycottProduct.getUuid());
        result.setName(boycottProduct.getName());
        result.setQrCode(boycottProduct.getQrCode());
        result.setImageUrl(boycottProduct.getImageUrl());
        
        if (boycottProduct.getProductCategory() != null) {
            ProductCategoryDto categoryDto = new ProductCategoryDto();
            categoryDto.setUuid(boycottProduct.getProductCategory().getUuid());
            categoryDto.setName(boycottProduct.getProductCategory().getName());
            categoryDto.setDescription(boycottProduct.getProductCategory().getDescription());
            result.setCategory(categoryDto);
        }
        
        // Get alternatives for boycott product
        List<AlternativeProduct> alternatives = alternativeProductRepository.findActiveAlternativesByBoycottProductId(boycottProduct.getUuid());
        if (!alternatives.isEmpty()) {
            List<AlternativeProductDto> alternativeDtos = alternatives.stream()
                    .map(this::mapToAlternativeProductDto)
                    .collect(Collectors.toList());
            result.setAlternatives(alternativeDtos);
        }
        
        return result;
    }

    private ProductSearchResultDto mapToProductSearchResult(AlternativeProduct alternativeProduct) {
        ProductSearchResultDto result = new ProductSearchResultDto();
        result.setProductType(ProductSearchResultDto.ProductType.ALTERNATIVE.name());
        result.setUuid(alternativeProduct.getUuid());
        result.setName(alternativeProduct.getName());
        result.setQrCode(alternativeProduct.getQrCode());
        result.setImageUrl(alternativeProduct.getImageUrl());
        
        return result;
    }

    private AlternativeProductDto mapToAlternativeProductDto(AlternativeProduct alternative) {
        AlternativeProductDto dto = new AlternativeProductDto();
        dto.setUuid(alternative.getUuid());
        dto.setName(alternative.getName());
        dto.setQrCode(alternative.getQrCode());
        dto.setImageUrl(alternative.getImageUrl());
        dto.setActive(alternative.isActive());
        return dto;
    }
}
