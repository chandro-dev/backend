package ara.main.Repository;

import ara.main.Entity.persons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<persons,Long> {
    Optional<persons> findByUsername(String username);
}
