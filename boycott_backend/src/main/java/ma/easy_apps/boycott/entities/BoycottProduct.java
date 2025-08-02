package ma.easy_apps.boycott.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "boycott_products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BoycottProduct extends BaseEntity<BoycottProduct> {
    private boolean isActive;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String qrCode;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory productCategory;
    @OneToMany(mappedBy = "boycottProduct",fetch = FetchType.LAZY)
    private List<AlternativeProduct> alternativeProducts;
}
