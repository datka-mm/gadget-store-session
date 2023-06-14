package peaksoft.gadgetstoresession.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.gadgetstoresession.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
