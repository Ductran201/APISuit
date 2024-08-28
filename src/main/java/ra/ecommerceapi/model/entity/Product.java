package ra.ecommerceapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private String image;
    private Boolean status;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
