package peaksoft.gadgetstoresession.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(
            generator = "brand_gen",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "brand_gen",
            sequenceName = "brand_seq",
            allocationSize = 1
    )
    private Long id;
    private String brandName;
    private String image;

    @OneToMany(cascade = ALL, mappedBy = "brand")
    private List<Product> products;
}
