package ara.main.Service;

import ara.main.Entity.Category;
import ara.main.Entity.Product;
import ara.main.Repositories.CategoryRepository;
import ara.main.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {


    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public ProductRepository  productRepository;
    public ResponseEntity<List<Category>> getAll()
    {
        try{
            return ResponseEntity.ok(categoryRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
    public ResponseEntity<List<Product>> getToCategory(int id){
        try{
            return ResponseEntity.ok(productRepository.findByCategory(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
