package ara.main.Service;

import ara.main.Dto.AuthenticationResponse;
import ara.main.Dto.RegisterGoogleResponse;
import ara.main.Dto.RegisterRequest;
import ara.main.Dto.util.Role;
import ara.main.Entity.persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2Service {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PersonasService personasService;
    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        // Obtener la información del usuario autenticado
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("sub");

        // Aquí puedes realizar acciones con la información del usuario, como almacenarla en una base de datos o devolverla como respuesta
        return ResponseEntity.ok("hola: " + email + "! Tu email es: " + principal.toString());
    }

    public ResponseEntity<RegisterGoogleResponse> registerUser(persons principal){
        RegisterRequest persona = new RegisterRequest();
        persona.setEmail(principal.getEmail());
        persona.setName(principal.getName());
        persona.setSecondName(principal.getSecondName());
        persona.setLastname(principal.getLastname());
        persona.setSecondLastname(principal.getSecondLastname());
        persona.setIdentification(principal.getIdentification());
        persona.setRole(Role.CUSTOMER);
        String message= String.valueOf(personasService.register(persona));
        var response = RegisterGoogleResponse.builder()
                .message(message)
                .idGoogle(principal.getIdentification())
                .build();
        return ResponseEntity.ok(response);
    }

    public  ResponseEntity<AuthenticationResponse> loginUser( String principal) {
        AuthenticationResponse jwtDto= authenticationService.loginOauth(principal);
        return ResponseEntity.ok(jwtDto);
    }

}
