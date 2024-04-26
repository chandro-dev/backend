package ara.main.Controller;

import ara.main.Entity.Orders;
import ara.main.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(Orders orders){
        return ordersService.register(orders);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(Orders orders){
        return ordersService.modify(orders);
    }
}
