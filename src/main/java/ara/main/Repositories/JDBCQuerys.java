package ara.main.Repositories;

import ara.main.Entity.OrderDetails;
import ara.main.Entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JDBCQuerys {
    private final JdbcTemplate jdbcTemplate;

    public List<Product> findProduct(String character){
        try{
            String sql = "SELECT * FROM product WHERE name LIKE ?";
            List<Product> ListProduct = jdbcTemplate.query(sql, new Object[] { "%" + character + "%"  } ,(resultSet, rowNum) -> {
                Product product = new Product();
                product.setIdProduct(BigInteger.valueOf(resultSet.getLong("id_product")));
                product.setName(resultSet.getString("name"));
                product.setQuantityAvalaible(resultSet.getInt("quantity_avalaible"));
                product.setCategory(resultSet.getInt("category"));
                product.setState(resultSet.getInt("state"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescribe(resultSet.getString("describe"));
                product.setPackaging(resultSet.getInt("packaging"));
                product.setBrand(resultSet.getInt("brand"));
                product.setDiscount(resultSet.getDouble("discount"));
                product.setImg_src(resultSet.getString("img_src"));
                product.setContent(resultSet.getString("content"));
                return product;
            });
            return ListProduct;
        }catch (Exception e){
            return null;
        }
    }

    public List<BigInteger> getMostPurchased(String identfication){
        try{
            String sql= """
                    SELECT id_product FROM order_details
                    where id_order=(
                    	SELECT id_orders FROM orders
                    	where identification = ?
                    )
                    """;
            List<BigInteger> product= jdbcTemplate.query(sql,new Object[] { identfication },(resultSet,rowNum)-> BigInteger.valueOf(resultSet.getLong("id_product")));
            return product;
        }catch (Exception e){
            return null;
        }
    }
}
