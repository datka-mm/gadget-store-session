package peaksoft.gadgetstoresession.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "baskets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket {

    @Id
    @GeneratedValue(
            generator = "basket_gen",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "basket_gen",
            sequenceName = "basket_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToMany(cascade = {DETACH, REFRESH, MERGE}, mappedBy = "baskets")
    private List<Product> products;

    @OneToOne(cascade = {DETACH, REFRESH, MERGE})
    private User user;
}
