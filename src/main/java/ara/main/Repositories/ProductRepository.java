package ara.main.Repositories;

import ara.main.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {
}
