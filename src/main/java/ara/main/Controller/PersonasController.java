package ara.main.Controller;

import ara.main.Entity.persons;
import ara.main.Service.PersonasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonasController {
    @Autowired
    private PersonasService personasService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid persons authRequest){
        System.out.println("Hola");
        return personasService.register(authRequest);
    }
    @GetMapping()
    public ResponseEntity<List<persons>> getAll(){
        return  personasService.getAll();
    }
}
