package ma.easy_apps.boycott.services;

import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.utils.GenericResponse;

import java.util.List;

public interface ProductCategoryService {
    
    /**
     * Create a new product category
     */
    GenericResponse<ProductCategory> createProductCategory(String name, String description);
    
    /**
     * Get all product categories
     */
    GenericResponse<List<ProductCategory>> getAllProductCategories();
    
    /**
     * Get product category by ID
     */
    GenericResponse<ProductCategory> getProductCategoryById(String id);
    
    /**
     * Get product category by name
     */
    GenericResponse<ProductCategory> getProductCategoryByName(String name);
    
    /**
     * Update product category
     */
    GenericResponse<ProductCategory> updateProductCategory(String id, String name, String description);
}
