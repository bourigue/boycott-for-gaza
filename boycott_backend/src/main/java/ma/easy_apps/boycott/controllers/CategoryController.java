package ma.easy_apps.boycott.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.easy_apps.boycott.dto.input.CreateCategoryInput;
import ma.easy_apps.boycott.dto.response.CategoryResponse;
import ma.easy_apps.boycott.services.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CategoryController {

    private final ProductCategoryService categoryService;

    /**
     * Get all categories
     * GET /api/categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        log.info("Getting all categories");
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Get category by ID
     * GET /api/categories/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id) {
        log.info("Getting category by ID: {}", id);
        Optional<CategoryResponse> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get category by name
     * GET /api/categories/name/{name}
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponse> getCategoryByName(@PathVariable String name) {
        log.info("Getting category by name: {}", name);
        Optional<CategoryResponse> category = categoryService.getCategoryByName(name);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Search categories by name
     * GET /api/categories/search?term=searchTerm
     */
    @GetMapping("/search")
    public ResponseEntity<List<CategoryResponse>> searchCategories(@RequestParam String term) {
        log.info("Searching categories with term: {}", term);
        List<CategoryResponse> categories = categoryService.searchCategories(term);
        return ResponseEntity.ok(categories);
    }

    /**
     * Create a new category
     * POST /api/categories
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryInput input) {
        log.info("Creating category: {}", input);
        CategoryResponse response = categoryService.createCategory(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Update an existing category
     * PUT /api/categories/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable String id,
            @Valid @RequestBody CreateCategoryInput input
    ) {
        log.info("Updating category with ID: {}", id);
        CategoryResponse response = categoryService.updateCategory(id, input);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a category
     * DELETE /api/categories/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        log.info("Deleting category with ID: {}", id);
        boolean deleted = categoryService.deleteCategory(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
