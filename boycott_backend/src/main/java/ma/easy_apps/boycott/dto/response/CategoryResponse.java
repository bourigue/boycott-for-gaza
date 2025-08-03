package ma.easy_apps.boycott.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.easy_apps.boycott.entities.ProductCategory;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static CategoryResponse fromEntity(ProductCategory category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getCreatedAt(),
            category.getUpdatedAt()
        );
    }
}
