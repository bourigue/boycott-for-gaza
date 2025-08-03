package ma.easy_apps.boycott.repositories;

import ma.easy_apps.boycott.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    
    /**
     * Search products by name containing the search term (case-insensitive)
     */
    @Query("SELECT p FROM products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND p.isActive = true")
    Page<Product> searchProductsByName(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    /**
     * Search products by name or QR code containing the search term (case-insensitive)
     */
    @Query("SELECT p FROM products p WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.qrCode) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND p.isActive = true")
    Page<Product> searchProducts(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    /**
     * Find products by category name
     */
    @Query("SELECT p FROM products p WHERE LOWER(p.category.name) = LOWER(:categoryName) AND p.isActive = true")
    Page<Product> findByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);
    
    /**
     * Find products by category name (exact match, case-insensitive)
     */
    List<Product> findByCategoryNameIgnoreCaseAndIsActiveTrue(String categoryName);
    
    /**
     * Find product by QR code
     */
    Optional<Product> findByQrCodeAndIsActiveTrue(String qrCode);
    
    /**
     * Find all active products
     */
    Page<Product> findByIsActiveTrueOrderByCreatedAtDesc(Pageable pageable);
    
    /**
     * Find products by boycott status
     */
    Page<Product> findByIsBoycottAndIsActiveTrueOrderByCreatedAtDesc(boolean isBoycott, Pageable pageable);
    
    /**
     * Check if product with QR code exists
     */
    boolean existsByQrCodeAndIsActiveTrue(String qrCode);
}
