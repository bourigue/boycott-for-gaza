package ma.easy_apps.boycott.repos;

import ma.easy_apps.boycott.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    Optional<ProductCategory> findByName(String name);
}
