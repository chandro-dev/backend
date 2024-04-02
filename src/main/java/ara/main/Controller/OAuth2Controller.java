package ara.main.Controller;
import ara.main.Dto.AuthenticationResponse;
import ara.main.Dto.util.Role;
import ara.main.Service.AuthenticationService;
import ara.main.Entity.persons;
import ara.main.Service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/Oauth")
public class OAuth2Controller {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired

    private PersonasService personasService;
    @GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        // Obtener la información del usuario autenticado
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("sub");

        // Aquí puedes realizar acciones con la información del usuario, como almacenarla en una base de datos o devolverla como respuesta
        return "hola: " + email + "! Tu email es: " + principal.toString();
    }
    @GetMapping("/register")
    public ResponseEntity<String> register(@AuthenticationPrincipal OAuth2User principal){
        System.out.append("Pase por aqui");
        persons persona = new persons();
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
    @GetMapping("/login")
  public  ResponseEntity<AuthenticationResponse> login(@AuthenticationPrincipal OAuth2User principal) {
        AuthenticationResponse jwtDto= authenticationService.loginOauth(principal.getAttribute("sub"));
        return ResponseEntity.ok(jwtDto);

    }
}