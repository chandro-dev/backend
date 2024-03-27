package ara.main.Repositories;

import ara.main.Entity.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailsRepository {
    private final JdbcTemplate jdbcTemplate;

    public void saveOrderDetails(OrderDetails orderDetails){
        try{
            String Sql="INSERT INTO order_details VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(Sql,orderDetails.getIdOrder(),orderDetails.getIdProduct(),orderDetails.getAmount()
                    ,orderDetails.getPriceTaxes());
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  void updateOrderDetails(OrderDetails orderDetails){
        try{
            String Sql="UPDATE order_details SET id_product=?, SET amount=?, SET price_taxes=? WHERE id_order=?";
            jdbcTemplate.update(Sql,orderDetails.getIdOrder(),orderDetails.getIdProduct(),orderDetails.getAmount()
                    ,orderDetails.getPriceTaxes());
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<OrderDetails> findByIdOrdersDetails(String idOrder){
        try{
            String Sql="SELECT * FROM order_details WHERE id_order=?";
            List<OrderDetails> orders= jdbcTemplate.query(Sql,new Object[] { idOrder },(resultSet,rowNum)->{
                OrderDetails orderDetails= new OrderDetails();
                orderDetails.setIdProduct(BigInteger.valueOf(resultSet.getInt("id_product")));
                orderDetails.setAmount(resultSet.getInt("amount"));
                orderDetails.setPriceTaxes(resultSet.getDouble("price_taxes"));
                return orderDetails;
            });
            return orders;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<OrderDetails> findAllOrdersDetails(){
        try{
            String Sql="SELECT * FROM order_details";
            List<OrderDetails> orders= jdbcTemplate.query(Sql,(resultSet,rowNum)->{
                OrderDetails orderDetails= new OrderDetails();
                orderDetails.setIdProduct(BigInteger.valueOf(resultSet.getInt("id_product")));
                orderDetails.setAmount(resultSet.getInt("amount"));
                orderDetails.setPriceTaxes(resultSet.getDouble("price_taxes"));
                return orderDetails;
            });
            return orders;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
