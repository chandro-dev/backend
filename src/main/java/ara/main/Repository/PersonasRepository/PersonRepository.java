package ara.main.Repository.PersonasRepository;
import ara.main.Entity.personas.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PersonRepository extends JpaRepository<Persons,Long> {
    Optional<Persons> findByUsername(String username);
    Boolean existsByUsername(String username);
}
