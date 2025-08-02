package ma.easy_apps.boycott.services;

import ma.easy_apps.boycott.dto.ProductSearchResultDto;
import ma.easy_apps.boycott.dto.CreateBoycottProductRequestDto;
import ma.easy_apps.boycott.entities.BoycottProduct;
import ma.easy_apps.boycott.utils.GenericResponse;

import java.util.List;

public interface BoycottProductService {
    
    /**
     * Search for a product by QR code
     * Returns boycott product with alternatives, alternative product, or not found
     */
    GenericResponse<ProductSearchResultDto> searchProductByQrCode(String qrCode);
    
    /**
     * Create a new boycott product with its alternatives
     */
    GenericResponse<BoycottProduct> createBoycottProduct(CreateBoycottProductRequestDto request);
    
    /**
     * Get all active boycott products
     */
    GenericResponse<List<BoycottProduct>> getAllActiveBoycottProducts();
    
    /**
     * Get boycott product by ID
     */
    GenericResponse<BoycottProduct> getBoycottProductById(String id);
    
    /**
     * Update boycott product status
     */
    GenericResponse<BoycottProduct> updateBoycottProductStatus(String id, boolean isActive);
}
