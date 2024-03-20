package ara.main.Entity.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product", schema = "public")
public class Product {
    @Id
    private String id_product;
    private  String name;

}
