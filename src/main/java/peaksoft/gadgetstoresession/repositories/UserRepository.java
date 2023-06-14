package peaksoft.gadgetstoresession.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.gadgetstoresession.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);
}