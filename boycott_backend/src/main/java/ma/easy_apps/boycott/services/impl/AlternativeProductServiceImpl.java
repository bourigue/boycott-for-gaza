package ma.easy_apps.boycott.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.CreateAlternativeProductDto;
import ma.easy_apps.boycott.entities.AlternativeProduct;
import ma.easy_apps.boycott.entities.BoycottProduct;
import ma.easy_apps.boycott.repos.AlternativeProductRepository;
import ma.easy_apps.boycott.repos.BoycottProductRepository;
import ma.easy_apps.boycott.services.AlternativeProductService;
import ma.easy_apps.boycott.utils.GenericResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlternativeProductServiceImpl implements AlternativeProductService {

    private final AlternativeProductRepository alternativeProductRepository;
    private final BoycottProductRepository boycottProductRepository;

    @Override
    @Transactional
    public GenericResponse<AlternativeProduct> createAlternativeProduct(CreateAlternativeProductDto request, String boycottProductId) {
        try {
            log.info("Creating alternative product: {}", request.getName());
            
            // Validate boycott product exists
            Optional<BoycottProduct> boycottProduct = boycottProductRepository.findById(boycottProductId);
            if (boycottProduct.isEmpty()) {
                return GenericResponse.error("Boycott product not found");
            }
            
            // Check if QR code already exists
            Optional<AlternativeProduct> existingAlternative = alternativeProductRepository.findByQrCodeAndActiveTrue(request.getQrCode());
            if (existingAlternative.isPresent()) {
                return GenericResponse.error("QR code already exists for another alternative product");
            }
            
            Optional<BoycottProduct> existingBoycott = boycottProductRepository.findByQrCodeAndActiveTrue(request.getQrCode());
            if (existingBoycott.isPresent()) {
                return GenericResponse.error("QR code already exists for another boycott product");
            }
            
            // Create alternative product
            AlternativeProduct alternativeProduct = new AlternativeProduct();
            alternativeProduct.setName(request.getName());
            alternativeProduct.setQrCode(request.getQrCode());
            alternativeProduct.setImageUrl(request.getImageUrl());
            alternativeProduct.setActive(true);
            
            AlternativeProduct savedProduct = alternativeProductRepository.save(alternativeProduct);
            return GenericResponse.success(savedProduct, "Alternative product created successfully");
            
        } catch (Exception e) {
            log.error("Error creating alternative product", e);
            return GenericResponse.error("Error creating alternative product: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<List<AlternativeProduct>> getAllActiveAlternativeProducts() {
        try {
            List<AlternativeProduct> products = alternativeProductRepository.findByIsActiveTrue();
            return GenericResponse.success(products, "Active alternative products retrieved successfully");
        } catch (Exception e) {
            log.error("Error retrieving active alternative products", e);
            return GenericResponse.error("Error retrieving active alternative products: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<List<AlternativeProduct>> getAlternativesByBoycottProductId(String boycottProductId) {
        try {
            List<AlternativeProduct> alternatives = alternativeProductRepository.findActiveAlternativesByBoycottProductId(boycottProductId);
            return GenericResponse.success(alternatives, "Alternatives retrieved successfully");
        } catch (Exception e) {
            log.error("Error retrieving alternatives for boycott product: {}", boycottProductId, e);
            return GenericResponse.error("Error retrieving alternatives: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<AlternativeProduct> getAlternativeProductById(String id) {
        try {
            Optional<AlternativeProduct> product = alternativeProductRepository.findById(id);
            if (product.isPresent()) {
                return GenericResponse.success(product.get(), "Alternative product found");
            } else {
                return GenericResponse.error("Alternative product not found");
            }
        } catch (Exception e) {
            log.error("Error retrieving alternative product by id: {}", id, e);
            return GenericResponse.error("Error retrieving alternative product: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<AlternativeProduct> updateAlternativeProductStatus(String id, boolean isActive) {
        try {
            Optional<AlternativeProduct> productOpt = alternativeProductRepository.findById(id);
            if (productOpt.isEmpty()) {
                return GenericResponse.error("Alternative product not found");
            }
            
            AlternativeProduct product = productOpt.get();
            product.setActive(isActive);
            product.setUpdatedAt(LocalDateTime.now());
            
            AlternativeProduct savedProduct = alternativeProductRepository.save(product);
            return GenericResponse.success(savedProduct, "Alternative product status updated successfully");
            
        } catch (Exception e) {
            log.error("Error updating alternative product status", e);
            return GenericResponse.error("Error updating alternative product status: " + e.getMessage());
        }
    }
}
