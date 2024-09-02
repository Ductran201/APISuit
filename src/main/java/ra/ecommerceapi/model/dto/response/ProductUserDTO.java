package ra.ecommerceapi.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductUserDTO {

    private String name;
    private String description;
    private String image;
    private String categoryName;

}
