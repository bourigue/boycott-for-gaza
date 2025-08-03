package ma.easy_apps.boycott.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.easy_apps.boycott.entities.base.BaseEntity;

import java.util.List;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ProductCategory extends BaseEntity<ProductCategory> {
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Product> products;
}
