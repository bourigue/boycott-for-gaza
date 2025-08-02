package ma.easy_apps.boycott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlternativeProductDto {
    private String name;
    private String qrCode;
    private String imageUrl;
}
