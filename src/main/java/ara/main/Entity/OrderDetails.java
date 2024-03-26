package ara.main.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigInteger;

@Data
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {
    @Column(name = "id_order")
    private String idOrder;
    @Column(name = "id_product")
    private BigInteger idProduct;
    private int amount;
    @Column(name = "price_taxes")
    private double priceTaxes;
}
