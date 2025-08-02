package ma.easy_apps.boycott.repos;

import ma.easy_apps.boycott.entities.BoycottProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoycottProductRepository extends JpaRepository<BoycottProduct, String> {
    
    @Query("SELECT bp FROM boycott_products bp WHERE bp.qrCode = :qrCode AND bp.isActive = true")
    Optional<BoycottProduct> findByQrCodeAndActiveTrue(@Param("qrCode") String qrCode);
    
    List<BoycottProduct> findByIsActiveTrue();
    
    Optional<BoycottProduct> findByName(String name);
}
