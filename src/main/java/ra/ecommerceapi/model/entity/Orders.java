package ra.ecommerceapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ra.ecommerceapi.model.base.BaseObject;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders extends BaseObject {

    private Double totalPrice;
    private String note;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    private Date receivedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
