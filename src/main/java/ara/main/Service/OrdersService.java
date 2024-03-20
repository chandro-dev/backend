package ara.main.Service;

import ara.main.Entity.Orders;
import ara.main.Entity.Product;
import ara.main.Repositories.OrdersRepository;
import ara.main.Repositories.ProductRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrdersService {
    private OrdersRepository ordersRepository;
    public ResponseEntity<String> register(Orders orders){
        try {
            if (ordersRepository.existsById(orders.getIdOrder())){

                return ResponseEntity.badRequest().body("Ya existe esta orden");
            }

            ordersRepository.save(orders);
            return ResponseEntity.ok("El producto fue registrado con exito");
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    public ResponseEntity<List<Orders>> getAll(){
        try{
            return ResponseEntity.ok(ordersRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
