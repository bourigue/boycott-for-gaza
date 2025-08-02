package ma.easy_apps.boycott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoycottProductRequestDto {
    private String name;
    private String qrCode;
    private String imageUrl;
    private String categoryId;
    private List<CreateAlternativeProductDto> alternatives;
}
