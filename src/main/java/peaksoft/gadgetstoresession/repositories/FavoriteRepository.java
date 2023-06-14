package peaksoft.gadgetstoresession.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.gadgetstoresession.models.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
