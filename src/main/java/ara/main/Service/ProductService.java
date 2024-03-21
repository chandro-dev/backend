package ara.main.Service;

import ara.main.Entity.Product;
import ara.main.Repositories.ProductRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductService {

    private ProductRepository  productRepository;
    public ResponseEntity<String> register(Product product){
        try {
            if (productRepository.existsById(product.getIdProduct())){

                return ResponseEntity.badRequest().body("Ya existe ese producto");
            }

            productRepository.save(product);
            return ResponseEntity.ok("El producto fue registrado con exito");
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    public ResponseEntity<List<Product>> getAll(){
        try{
            return ResponseEntity.ok(productRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
