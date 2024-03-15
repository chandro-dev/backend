package ara.main.Controller;

import ara.main.Dto.AuthenticationRequest;
import ara.main.Dto.AuthenticationResponse;
import ara.main.Entity.PersonEntities.persons;
import ara.main.Repositories.InventoryRepository.InventarioRepositorio;
import ara.main.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private InventarioRepositorio inventarioRepositorio;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest){
        AuthenticationResponse jwtDto= authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid persons authRequest){
        return authenticationService.register(authRequest);
    }

    @GetMapping()
    public List<String> getInventario(){
        return inventarioRepositorio.getBrandTableColumns();
    }
}
