package ara.main.Controller;

import ara.main.Entity.Product;
import ara.main.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/register")
    public ResponseEntity<String> registerProduct(@RequestBody @Valid Product product){
        return productService.register(product);
    }
    @PutMapping("/modify")
    public  ResponseEntity<String>modifyProduct(@RequestBody @Valid Product product){
        return productService.modify(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable BigInteger id){
        return productService.delete(id);
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        return  productService.getAll();
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Product>>findProduct(@PathVariable String name){
        return productService.findProducts(name);
    }
}
