package ra.ecommerceapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ra.ecommerceapi.model.base.BaseObject;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
@Entity
public class Category extends BaseObject {

    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String description;
    private String image;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
}
