package ara.main.Controller;

import ara.main.Entity.Category;
import ara.main.Entity.Product;
import ara.main.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping()
    public ResponseEntity<List<Category>> getAll(){
        return  categoryService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getProductbyCategory(@PathVariable int id) {
        return categoryService.getToCategory(id);
    }
}
