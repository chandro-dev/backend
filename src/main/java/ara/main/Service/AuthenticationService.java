package ara.main.Service;

import ara.main.Dto.AuthenticationRequest;
import ara.main.Dto.AuthenticationResponse;
import ara.main.Entity.personas.Persons;
import ara.main.Repository.PersonasRepository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

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

        Persons persons = personRepository.findByUsername(authRequest.getUsername()).get();

        String jwt=jwtService.generateToken(persons,generateExtraClaims(persons));
        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(Persons persons) {
        Map<String,Object> extraClaims= new HashMap<>();
        extraClaims.put("name", persons.getName());
        extraClaims.put("role", persons.getRole().name());
        return extraClaims;
    }


}
