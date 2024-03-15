package ara.main.Service;

import ara.main.Dto.AuthenticationRequest;
import ara.main.Dto.AuthenticationResponse;
import ara.main.Entity.persons;
import ara.main.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private JwtService jwtService;
    public AuthenticationResponse login(AuthenticationRequest authRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),authRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);

        persons persons = personRepository.findByUsername(authRequest.getUsername()).get();

        String jwt=jwtService.generateToken(persons,generateExtraClaims(persons));
        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(persons persons) {
        Map<String,Object> extraClaims= new HashMap<>();
        extraClaims.put("name", persons.getName());
        extraClaims.put("role", persons.getRole().name());
        return extraClaims;
    }

    public ResponseEntity<String> register(persons _person){
        try {
            if (personRepository.existsByUsername(_person.getUsername())){

                return ResponseEntity.badRequest().body("Ya existe ese nombre de usuario");
            }
            //Enconded password
            String encodedPassword = passwordEncoder.encode(_person.getPassword());
            _person.setPassword(encodedPassword);
            personRepository.save(_person);
<<<<<<< HEAD
            return ResponseEntity.ok("El usuario fue");
=======
            return ResponseEntity.ok("Guardado Correc");
>>>>>>> 41ac661 (ServicioAutenticacion)
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
