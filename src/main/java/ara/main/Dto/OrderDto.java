package ara.main.Dto;

import ara.main.Entity.OrderDetails;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class OrderDto {
    private String idOrder;
    private int totalPrice;
    private String payment;
    private List<OrderDetails> totalProducts;
}
