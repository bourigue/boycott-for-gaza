package ma.easy_apps.boycott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlternativeProductDto {
    private String uuid;
    private String name;
    private String qrCode;
    private String imageUrl;
    private boolean isActive;
}
