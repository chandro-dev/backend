package ara.main.Service;

import ara.main.Config.GeneratorId;
import ara.main.Dto.OrderDto;
import ara.main.Entity.OrderDetails;
import ara.main.Entity.Orders;
import ara.main.Repositories.OrderDetailsRepository;
import ara.main.Repositories.OrdersRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService{
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private GeneratorId generatorId;
    @Autowired
    private JwtService jwtService;
    public ResponseEntity<String> register(OrderDto orderDto){
        String idOrder= generatorId.IdGenerator();
        if (!ordersRepository.existsById(idOrder)){
            //Crear en la orden
            var order=Orders.builder()
                    .idOrders(idOrder)
                    .totalPrice(0.0)
                    .statePayment(3)
                    .identification(jwtService.extractID(orderDto.getToken()))
                    .build();
            System.out.println(order.getIdentification());
            ordersRepository.save(order);
        }else{
            return ResponseEntity.badRequest().body("ya existe el id asociado");
        }
        for (var item: orderDto.getTotalProducts()){
            var orderDetails= OrderDetails.builder()
                    .idOrder(idOrder)
                    .idProduct(item.getIdProduct())
                    .amount(item.getAmount())
                    .priceTaxes(item.getPriceTaxes())
                    .build();
            orderDetailsRepository.saveOrderDetails(orderDetails);
        }
        return ResponseEntity.ok("Guardado con exito");
    }
    public ResponseEntity<List<OrderDetails>> getAll() {
        return ResponseEntity.ok(orderDetailsRepository.findAllOrdersDetails());
    }
}
