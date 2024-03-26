package ara.main.Repositories;

import ara.main.Entity.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDetailsRepository {
    private final JdbcTemplate jdbcTemplate;

    public void saveOrderDetails(OrderDetails orderDetails){
        String Sql="INSERT INTO order_details VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(Sql,orderDetails.getIdOrder(),orderDetails.getIdProduct(),orderDetails.getAmount()
                ,orderDetails.getPriceTaxes());
    }
}
