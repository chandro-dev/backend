package ara.main.Repositories.UserRepository;

import ara.main.Entity.PersonEntities.persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<persons,Long> {
    Optional<persons> findByUsername(String username);
    Boolean existsByUsername(String username);
}
