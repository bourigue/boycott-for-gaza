package ma.easy_apps.boycott.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product_categories")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ProductCategory extends BaseEntity<ProductCategory> {
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
}
