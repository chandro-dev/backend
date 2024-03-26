package ara.main.Service;

import ara.main.Config.GeneratorId;
import ara.main.Dto.OrderDto;
import ara.main.Entity.Orders;
import ara.main.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService implements CRUDInterface<OrderDto>{
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private GeneratorId generatorId;
    @Override
    public ResponseEntity<String> register(OrderDto orderDetails) {
        if (!ordersRepository.existsById(orderDetails.getIdOrder())){
            String Id=generatorId.IdGenerator();
            var order=Orders.builder()
                    .idOrder(Id)
                    .payment("")
                    .totalPrice(0.0)
                    .build();
            ordersRepository.save(order);
            return ResponseEntity.ok(Id);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> modify(OrderDto orderDetails) {
        if (ordersRepository.existsById(orderDetails.getIdOrder())){
            String Id=generatorId.IdGenerator();
            var order=Orders.builder()
                    .idOrder(Id)
                    .payment(orderDetails.getPayment())
                    .totalPrice(orderDetails.getTotalPrice())
                    .build();
            ordersRepository.save(order);
            return ResponseEntity.ok(Id);
        }
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAll() {
        return null;
    }
}
