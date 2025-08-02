package ma.easy_apps.boycott.services;

import ma.easy_apps.boycott.dto.CreateAlternativeProductDto;
import ma.easy_apps.boycott.entities.AlternativeProduct;
import ma.easy_apps.boycott.utils.GenericResponse;

import java.util.List;

public interface AlternativeProductService {
    
    /**
     * Create a new alternative product
     */
    GenericResponse<AlternativeProduct> createAlternativeProduct(CreateAlternativeProductDto request, String boycottProductId);
    
    /**
     * Get all active alternative products
     */
    GenericResponse<List<AlternativeProduct>> getAllActiveAlternativeProducts();
    
    /**
     * Get alternative products by boycott product ID
     */
    GenericResponse<List<AlternativeProduct>> getAlternativesByBoycottProductId(String boycottProductId);
    
    /**
     * Get alternative product by ID
     */
    GenericResponse<AlternativeProduct> getAlternativeProductById(String id);
    
    /**
     * Update alternative product status
     */
    GenericResponse<AlternativeProduct> updateAlternativeProductStatus(String id, boolean isActive);
}
