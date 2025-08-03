package ma.easy_apps.boycott.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.input.CreateCategoryInput;
import ma.easy_apps.boycott.dto.response.CategoryResponse;
import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    /**
     * Get all categories
     */
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllByOrderByNameAsc()
            .stream()
            .map(CategoryResponse::fromEntity)
            .collect(Collectors.toList());
    }

    /**
     * Get category by ID
     */
    public Optional<CategoryResponse> getCategoryById(String id) {
        return categoryRepository.findById(id)
            .map(CategoryResponse::fromEntity);
    }

    /**
     * Get category by name
     */
    public Optional<CategoryResponse> getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name)
            .map(CategoryResponse::fromEntity);
    }

    /**
     * Search categories by name
     */
    public List<CategoryResponse> searchCategories(String searchTerm) {
        return categoryRepository.searchCategoriesByName(searchTerm)
            .stream()
            .map(CategoryResponse::fromEntity)
            .collect(Collectors.toList());
    }

    /**
     * Create a new category
     */
    @Transactional
    public CategoryResponse createCategory(CreateCategoryInput input) {
        log.info("Creating category with name: {}", input.getName());
        
        // Check if category already exists
        if (categoryRepository.existsByNameIgnoreCase(input.getName())) {
            throw new IllegalArgumentException("Category with name '" + input.getName() + "' already exists");
        }
        
        ProductCategory category = new ProductCategory();
        category.setName(input.getName());
        
        ProductCategory savedCategory = categoryRepository.save(category);
        log.info("Successfully created category with ID: {}", savedCategory.getId());
        
        return CategoryResponse.fromEntity(savedCategory);
    }

    /**
     * Update category
     */
    @Transactional
    public CategoryResponse updateCategory(String id, CreateCategoryInput input) {
        ProductCategory category = categoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
        
        // Check if another category with the same name exists
        if (!category.getName().equalsIgnoreCase(input.getName()) && 
            categoryRepository.existsByNameIgnoreCase(input.getName())) {
            throw new IllegalArgumentException("Category with name '" + input.getName() + "' already exists");
        }
        
        category.setName(input.getName());
        ProductCategory savedCategory = categoryRepository.save(category);
        log.info("Successfully updated category with ID: {}", savedCategory.getId());
        
        return CategoryResponse.fromEntity(savedCategory);
    }

    /**
     * Delete category
     */
    @Transactional
    public boolean deleteCategory(String id) {
        Optional<ProductCategory> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            ProductCategory category = categoryOpt.get();
            
            // Check if category has products
            if (category.getProducts() != null && !category.getProducts().isEmpty()) {
                throw new IllegalArgumentException("Cannot delete category that has products associated with it");
            }
            
            categoryRepository.delete(category);
            log.info("Successfully deleted category with ID: {}", id);
            return true;
        }
        return false;
    }

    /**
     * Get or create category by name (used internally by ProductService)
     */
    @Transactional
    public ProductCategory getOrCreateCategory(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
            .orElseGet(() -> {
                log.info("Creating new category: {}", categoryName);
                ProductCategory newCategory = new ProductCategory();
                newCategory.setName(categoryName);
                return categoryRepository.save(newCategory);
            });
    }
}
