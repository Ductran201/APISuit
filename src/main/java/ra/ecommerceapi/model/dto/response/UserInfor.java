package ra.ecommerceapi.model.dto.response;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserInfor {
    private Long id;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String avatar;
    private String phone;
    private String address;
    private Boolean gender;
    private Date dob;
}
