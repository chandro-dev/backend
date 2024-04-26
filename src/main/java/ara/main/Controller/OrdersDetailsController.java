package ara.main.Controller;

import ara.main.Dto.OrderDto;
import ara.main.Service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ordersdetails")
public class OrdersDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/save")
    public ResponseEntity<String> saveOrderDetails(@RequestBody OrderDto orderDto){
        return orderDetailsService.register(orderDto);
    }
}
