package ra.ecommerceapi.model.dto.response;

import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JWTResponse {
    private String accessToken;
    private String fullName;
    private String email;
    private String phone;
    private Boolean status;
    private Set<String> roles;
}
