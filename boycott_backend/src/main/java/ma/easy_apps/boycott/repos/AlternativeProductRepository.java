package ma.easy_apps.boycott.repos;

import ma.easy_apps.boycott.entities.AlternativeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlternativeProductRepository extends JpaRepository<AlternativeProduct, String> {
    
    @Query("SELECT ap FROM alternative_products ap WHERE ap.qrCode = :qrCode AND ap.isActive = true")
    Optional<AlternativeProduct> findByQrCodeAndActiveTrue(@Param("qrCode") String qrCode);
    
    @Query("SELECT ap FROM alternative_products ap WHERE ap.boycottProduct.uuid = :boycottProductId AND ap.isActive = true")
    List<AlternativeProduct> findActiveAlternativesByBoycottProductId(@Param("boycottProductId") String boycottProductId);
    
    List<AlternativeProduct> findByIsActiveTrue();
    
    Optional<AlternativeProduct> findByName(String name);
}
