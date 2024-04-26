package ara.main.Service;

import ara.main.Config.GeneratorId;
import ara.main.Dto.PersonsDto;
import ara.main.Dto.RegisterRequest;
import ara.main.Dto.UpdatedRegisterRequest;
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
    @Autowired
    private GeneratorId generatorId;

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
                    .identification(generatorId.generatorNumericId())
                    .name(request.getName())
                    .secondName(request.getSecondLastname())
                    .lastname(request.getLastname())
                    .secondLastname(request.getSecondLastname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .role(request.getRole())
                    .dni(request.getDni())
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
    public ResponseEntity<String> continueRegister(UpdatedRegisterRequest request){
        if (personRepository.existsById(request.getIdentification())){
            persons person=personRepository.findById(request.getIdentification()).orElse(null);
            assert person != null;
            var personRegister = persons.builder()
                    .identification(request.getIdentification())
                    .name(person.getName())
                    .secondName(person.getSecondLastname())
                    .lastname(person.getLastname())
                    .secondLastname(person.getSecondLastname())
                    .username(request.getUsername())
                    .email(person.getEmail())
                    .password(person.getPassword())
                    .role(person.getRole())
                    .dni(request.getDni())
                    .build();
            personRepository.save(personRegister);
            return ResponseEntity.ok("Modificado Correctamente");
        }
        return ResponseEntity.badRequest().body("No se encontro la identificacion");
    }
}
