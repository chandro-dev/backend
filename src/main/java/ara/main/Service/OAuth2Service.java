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

    public ResponseEntity<RegisterGoogleResponse> registerUser(String principal,String name,String email){
        RegisterRequest persona = new RegisterRequest();
        String[] nombre= name.toString().split(" ");
        persona.setEmail(email);
        persona.setName(nombre[0]);
        persona.setSecondName(nombre[1]);
        persona.setLastname(nombre[2]);
        persona.setSecondLastname(nombre[3]);
        persona.setIdentification(principal);
        persona.setRole(Role.CUSTOMER);
        String message= String.valueOf(personasService.register(persona));
        var response = RegisterGoogleResponse.builder()
                .message(message)
                .idGoogle(principal)
                .build();
        return ResponseEntity.ok(response);
    }

    public  ResponseEntity<AuthenticationResponse> loginUser( String principal) {
        AuthenticationResponse jwtDto= authenticationService.loginOauth(principal);
        return ResponseEntity.ok(jwtDto);
    }

}
