package ara.main.Service;

import ara.main.Entity.Brand;
import ara.main.Entity.Category;
import ara.main.Entity.Product;
import ara.main.Repositories.BrandRepository;
import ara.main.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BrandService {
    @Autowired
    public ProductRepository  productRepository;

    @Autowired
    public BrandRepository brandRepository;
    public ResponseEntity<List<Brand>> getAll()
    {
        try{
            return ResponseEntity.ok(brandRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
    public ResponseEntity<List<Product>> getToCBrand(int id){
        try{
            return ResponseEntity.ok(productRepository.findByBrand(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
