package ma.easy_apps.boycott.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.easy_apps.boycott.entities.base.BaseEntity;

@Entity(name = "products")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Product> {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false, unique = true)
    private String qrCode;
    private boolean isBoycott;
    private boolean isActive;
    @ManyToOne
    private ProductCategory category;
}
