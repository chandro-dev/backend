package ara.main.Service;

import ara.main.Config.GeneratorId;
import ara.main.Entity.Orders;
import ara.main.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements CRUDInterface<Orders>{
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private GeneratorId generatorId;
    @Override
    public ResponseEntity<String> register(Orders order) {
        if (!ordersRepository.existsById(order.getIdOrders())){
            return ResponseEntity.ok("Guardado Correctamente"+saveOrder(order));
        }
        return ResponseEntity.badRequest().body("Ya existe una orden asociada a ese Id");
    }

    @Override
    public ResponseEntity<String> modify(Orders order) {
        if (ordersRepository.existsById(order.getIdOrders())){
            ResponseEntity.ok("Modificado Correctamente"+saveOrder(order));
        }
        return ResponseEntity.badRequest().body("No existe una orden asociada a ese Id");
    }

    @Override
    public ResponseEntity<List<Orders>> getAll() {
        return ResponseEntity.ok(ordersRepository.findAll());
    }

    protected Optional<Orders> getOrderById(String idOrder){
        return ordersRepository.findById(idOrder);
    }
    protected String saveOrder(Orders order){
        String Id=generatorId.IdGenerator();
        var orders=Orders.builder()
                .idOrders(Id)
                .totalPrice(order.getTotalPrice())
                .statePayment(3)
                .identification(order.getIdentification())
                .build();
        ordersRepository.save(orders);
        return Id;
    }
}
