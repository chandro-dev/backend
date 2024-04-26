package ara.main.Service;

import ara.main.Config.GeneratorId;
import ara.main.Entity.OrderDetails;
import ara.main.Entity.Orders;
import ara.main.Repositories.OrderDetailsRepository;
import ara.main.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService implements CRUDInterface<OrderDetails>{
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private GeneratorId generatorId;
    @Override
    public ResponseEntity<String> register(OrderDetails service) {
        if (ordersRepository.existsById(service.getIdOrder())){
            service.setIdOrder(generatorId.IdGenerator());
            orderDetailsRepository.saveOrderDetails(service);
            return ResponseEntity.ok("Guardado Correctamente");
        }else{
            //Creo la orden
            service.setIdOrder(generatorId.IdGenerator());
            var order=Orders.builder()
                    .idOrders(service.getIdOrder())
                    .totalPrice(service.getAmount())
                    .statePayment(3)
                    .build();
            ordersService.saveOrder(order);
            orderDetailsRepository.saveOrderDetails(service);
            return ResponseEntity.ok("Guardado Correctamente");
        }

    }

    @Override
    public ResponseEntity<String> modify(OrderDetails service) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDetails>> getAll() {
        return ResponseEntity.ok(orderDetailsRepository.findAllOrdersDetails());
    }
}
