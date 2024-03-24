package ara.main.Controller;

import ara.main.Entity.Brand;
import ara.main.Entity.Product;
import ara.main.Entity.persons;
import ara.main.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @GetMapping()
    public ResponseEntity<List<Brand>> getAll(){
        return  brandService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getProductbyBrand(@PathVariable int id) {
        return brandService.getToCBrand(id);
    }
}
