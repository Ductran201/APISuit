package ra.ecommerceapi.model.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserInformation {
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private String phone;
    private Boolean gender;
    private Date createdDate;
    private Date dob;
}
