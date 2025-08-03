package ma.easy_apps.boycott.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.easy_apps.boycott.entities.Product;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    
    private String id;
    private String name;
    private String imageUrl;
    private String qrCode;
    private boolean isBoycott;
    private boolean isActive;
    private String category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;
    
    public static ProductResponse fromEntity(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getImageUrl(),
            product.getQrCode(),
            product.isBoycott(),
            product.isActive(),
            product.getCategory() != null ? product.getCategory().getName() : null,
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}
