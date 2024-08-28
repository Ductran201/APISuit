package ra.ecommerceapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ra.ecommerceapi.model.constant.RoleName;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;
}
