package ara.main.Controller;

import ara.main.Entity.Product;
import ara.main.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid Product authRequest){
        return productService.register(authRequest);
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        return  productService.getAll();
    }
}
