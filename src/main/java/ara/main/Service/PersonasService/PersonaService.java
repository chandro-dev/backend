package ara.main.Service.PersonasService;
import ara.main.Entity.personas.Persons;
import ara.main.Repository.PersonasRepository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonRepository personaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public PersonaService(PersonRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void guardarPersona(Persons persona) {
        personaRepository.save(persona);
    }
    public ResponseEntity<String> register(Persons _person){
        try {
            if (personaRepository.existsByUsername(_person.getUsername())){

                return ResponseEntity.badRequest().body("Ya existe ese nombre de usuario");
            }
            //Enconded password
            String encodedPassword = passwordEncoder.encode(_person.getPassword());
            _person.setPassword(encodedPassword);
            personaRepository.save(_person);
            return ResponseEntity.ok("El usuario fue registrado con exito");
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    // Otros métodos de servicio que utilicen el repositorio
}
