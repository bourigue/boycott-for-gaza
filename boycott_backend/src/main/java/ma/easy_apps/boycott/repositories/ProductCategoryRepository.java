package ma.easy_apps.boycott.repositories;

import ma.easy_apps.boycott.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    
    /**
     * Find category by name (case-insensitive)
     */
    Optional<ProductCategory> findByNameIgnoreCase(String name);
    
    /**
     * Check if category exists by name
     */
    boolean existsByNameIgnoreCase(String name);
    
    /**
     * Search categories by name containing the search term
     */
    @Query("SELECT pc FROM ProductCategory pc WHERE LOWER(pc.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<ProductCategory> searchCategoriesByName(@Param("searchTerm") String searchTerm);
    
    /**
     * Find all categories ordered by name
     */
    List<ProductCategory> findAllByOrderByNameAsc();
}
