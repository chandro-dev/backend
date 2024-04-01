package ara.main.Service;

import ara.main.Entity.persons;
import ara.main.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<String> register(persons _person){
        try {
            if (personRepository.existsByUsername(_person.getUsername()) || personRepository.existsById(_person.getIdentification())){

                return ResponseEntity.badRequest().body("Ya existe este usuario");
            }
            //Enconded password
            if(_person.getPassword() !=null){
            String encodedPassword = passwordEncoder.encode(_person.getPassword());
            _person.setPassword(encodedPassword);
            }

            personRepository.save(_person);
            return ResponseEntity.ok("El usuario fue registrado con exito");
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    public ResponseEntity<List<persons>> getAll()
    {
        try{
            return ResponseEntity.ok(personRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
