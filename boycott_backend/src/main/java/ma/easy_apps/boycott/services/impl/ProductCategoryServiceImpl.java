package ma.easy_apps.boycott.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.repos.ProductCategoryRepository;
import ma.easy_apps.boycott.services.ProductCategoryService;
import ma.easy_apps.boycott.utils.GenericResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public GenericResponse<ProductCategory> createProductCategory(String name, String description) {
        try {
            log.info("Creating product category: {}", name);
            
            // Check if category with same name already exists
            Optional<ProductCategory> existingCategory = productCategoryRepository.findByName(name);
            if (existingCategory.isPresent()) {
                return GenericResponse.error("Product category with this name already exists");
            }
            
            ProductCategory category = new ProductCategory();
            category.setName(name);
            category.setDescription(description);
            
            ProductCategory savedCategory = productCategoryRepository.save(category);
            return GenericResponse.success(savedCategory, "Product category created successfully");
            
        } catch (Exception e) {
            log.error("Error creating product category", e);
            return GenericResponse.error("Error creating product category: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<List<ProductCategory>> getAllProductCategories() {
        try {
            List<ProductCategory> categories = productCategoryRepository.findAll();
            return GenericResponse.success(categories, "Product categories retrieved successfully");
        } catch (Exception e) {
            log.error("Error retrieving product categories", e);
            return GenericResponse.error("Error retrieving product categories: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<ProductCategory> getProductCategoryById(String id) {
        try {
            Optional<ProductCategory> category = productCategoryRepository.findById(id);
            if (category.isPresent()) {
                return GenericResponse.success(category.get(), "Product category found");
            } else {
                return GenericResponse.error("Product category not found");
            }
        } catch (Exception e) {
            log.error("Error retrieving product category by id: {}", id, e);
            return GenericResponse.error("Error retrieving product category: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<ProductCategory> getProductCategoryByName(String name) {
        try {
            Optional<ProductCategory> category = productCategoryRepository.findByName(name);
            if (category.isPresent()) {
                return GenericResponse.success(category.get(), "Product category found");
            } else {
                return GenericResponse.error("Product category not found");
            }
        } catch (Exception e) {
            log.error("Error retrieving product category by name: {}", name, e);
            return GenericResponse.error("Error retrieving product category: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<ProductCategory> updateProductCategory(String id, String name, String description) {
        try {
            Optional<ProductCategory> categoryOpt = productCategoryRepository.findById(id);
            if (categoryOpt.isEmpty()) {
                return GenericResponse.error("Product category not found");
            }
            
            // Check if another category with the same name exists
            Optional<ProductCategory> existingCategory = productCategoryRepository.findByName(name);
            if (existingCategory.isPresent() && !existingCategory.get().getUuid().equals(id)) {
                return GenericResponse.error("Another product category with this name already exists");
            }
            
            ProductCategory category = categoryOpt.get();
            category.setName(name);
            category.setDescription(description);
            category.setUpdatedAt(LocalDateTime.now());
            
            ProductCategory savedCategory = productCategoryRepository.save(category);
            return GenericResponse.success(savedCategory, "Product category updated successfully");
            
        } catch (Exception e) {
            log.error("Error updating product category", e);
            return GenericResponse.error("Error updating product category: " + e.getMessage());
        }
    }
}
