package ara.main.Service;

import ara.main.Dto.AuthenticationResponse;
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

    public ResponseEntity<String> registerUser(@AuthenticationPrincipal OAuth2User principal){
        RegisterRequest persona = new RegisterRequest();
        String[] nombre= principal.getAttribute("name").toString().split(" ");
        String[] apellido= principal.getAttribute("family_name").toString().split(" ");
        persona.setEmail(principal.getAttribute("email"));
        persona.setName(nombre[0]);
        persona.setSecondName(nombre[1]);
        persona.setLastname(apellido[0]);
        persona.setSecondLastname(apellido[1]);
        persona.setIdentification(principal.getAttribute("sub"));
        persona.setRole(Role.CUSTOMER);
        return personasService.register(persona);
    }

    public  ResponseEntity<AuthenticationResponse> loginUser( String principal) {
        AuthenticationResponse jwtDto= authenticationService.loginOauth(principal);
        return ResponseEntity.ok(jwtDto);
    }

}
