package ara.main.Repository.PersonasRepository;

import ara.main.Entity.personas.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
