package ara.main.Repositories;

import ara.main.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {
}
