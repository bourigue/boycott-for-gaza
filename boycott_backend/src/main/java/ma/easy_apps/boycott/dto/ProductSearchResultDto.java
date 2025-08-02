package ma.easy_apps.boycott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchResultDto {
    private String productType; // "BOYCOTT", "ALTERNATIVE", "NOT_FOUND"
    private String uuid;
    private String name;
    private String qrCode;
    private String imageUrl;
    private ProductCategoryDto category;
    private java.util.List<AlternativeProductDto> alternatives;
    
    public enum ProductType {
        BOYCOTT, ALTERNATIVE, NOT_FOUND
    }
}
