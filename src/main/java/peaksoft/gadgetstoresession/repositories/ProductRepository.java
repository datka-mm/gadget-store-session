package peaksoft.gadgetstoresession.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.gadgetstoresession.dto.response.ProductResponse;
import peaksoft.gadgetstoresession.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new peaksoft.gadgetstoresession.dto.response.ProductResponse(" +
            "p.id, " +
            "p.name, " +
            "p.price, " +
            "p.images, " +
            "p.characteristic, " +
            "p.isFavorite, " +
            "p.madeIn, " +
            "p.category) " +
            "from Product p ")
    List<ProductResponse> findAllProductResponses();
}