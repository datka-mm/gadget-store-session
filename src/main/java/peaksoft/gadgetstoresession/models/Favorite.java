package peaksoft.gadgetstoresession.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {

    @Id
    @GeneratedValue(
            generator = "favorite_gen",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "favorite_gen",
            sequenceName = "favorite_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    private User user;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Product product;
}
