package ara.main.Service;

import ara.main.Dto.PersonsDto;
import ara.main.Dto.RegisterRequest;
import ara.main.Entity.persons;
import ara.main.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonasService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> register(RegisterRequest request){
        try {
            if (personRepository.existsByUsername(request.getUsername()) && personRepository.existsById(request.getIdentification())){

                return ResponseEntity.badRequest().body("Ya existe este usuario");
            }
            //Enconded password
            if(request.getPassword() !=null){
                String encodedPassword = passwordEncoder.encode(request.getPassword());
                request.setPassword(encodedPassword);
            }else{
                return  ResponseEntity.badRequest().body("Contraseña Vacia");
            }
            var user = persons.builder()
                    .identification(request.getIdentification())
                    .name(request.getName())
                    .secondName(request.getSecondLastname())
                    .lastname(request.getLastname())
                    .secondLastname(request.getSecondLastname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .role(request.getRole())
                    .build();
            personRepository.save(user);
            return ResponseEntity.ok("El usuario fue registrado con exito");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    public ResponseEntity<List<persons>> getAll() {
        try{
            return ResponseEntity.ok(personRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
    public ResponseEntity<PersonsDto> getUser(String username){
        persons user= personRepository.findByUsername(username).orElse(null);
        if (user==null){
           return ResponseEntity.badRequest().body(new PersonsDto(null,null,null,null));
        }else{
            var profile = PersonsDto.builder()
                    .identification(user.getIdentification())
                    .name(user.getName())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .build();
            return ResponseEntity.ok(profile);
        }
    }
}
