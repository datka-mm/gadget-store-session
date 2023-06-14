package peaksoft.gadgetstoresession.models;


import jakarta.persistence.*;
import lombok.*;
import peaksoft.gadgetstoresession.enums.Category;

import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(
            generator = "product_gen",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "product_gen",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private int price;

    @ElementCollection
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    private Category category;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Brand brand;

    @OneToMany(cascade = ALL, mappedBy = "product")
    private List<Favorite> favorites;

    @OneToMany(cascade = ALL, mappedBy = "product")
    private List<Comment> comments;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH})
    private List<Basket> baskets;

    public Product(String name, int price, List<String> images, String characteristic, Boolean isFavorite, String madeIn, Category category, Brand brand) {
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brand = brand;
    }
}
