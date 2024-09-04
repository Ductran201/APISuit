package ra.ecommerceapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ra.ecommerceapi.model.base.BaseObject;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product extends BaseObject {

    @Column(unique = true)
    private String name;
    private String description;
    private String image;
    private Double price;
    private Integer stock;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
