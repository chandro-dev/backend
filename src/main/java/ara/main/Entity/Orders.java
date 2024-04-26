package ara.main.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @Column(name = "id_orders")
    private String idOrders;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "state_payment")
    private int statePayment;
    private String identification;
}
