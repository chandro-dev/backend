package ara.main.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "id_product")
    private BigInteger idProduct;
    private String name;
    @Column(name = "quantity_avalaible")
    private int quantityAvalaible;
    private int category;
    private int state;
    private double price;
    private String describe;
    private int packaging;
    private int brand;
    private double discount;
    private String img_src;
}
