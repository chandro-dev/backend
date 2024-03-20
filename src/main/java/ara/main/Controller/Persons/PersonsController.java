package ara.main.Controller.Persons;
import ara.main.Entity.personas.Persons;
import ara.main.Service.PersonasService.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonsController {
    @Autowired
    private PersonaService PersonaService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid Persons authRequest){
        return PersonaService.register(authRequest);
    }
}
