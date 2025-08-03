package ma.easy_apps.boycott.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductInput {
    
    @NotBlank(message = "Product name is required")
    private String name;
    
    @NotBlank(message = "QR code is required")
    private String qrCode;
    
    @NotBlank(message = "Category name is required")
    private String categoryName;
    
    @NotNull(message = "Boycott status is required")
    private Boolean isBoycott;
    
    private String imageUrl;
    
    private Boolean isActive = true;
}
