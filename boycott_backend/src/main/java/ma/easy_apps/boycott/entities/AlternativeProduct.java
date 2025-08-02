package ma.easy_apps.boycott.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "alternative_products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AlternativeProduct extends BaseEntity<AlternativeProduct> {
    private boolean isActive;
    private String name;
    private String qrCode;
    private String imageUrl;
    @ManyToOne()
    private BoycottProduct boycottProduct;


}
