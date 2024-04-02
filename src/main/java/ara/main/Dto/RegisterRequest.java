package ara.main.Dto;

import ara.main.Dto.util.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String identification;
    private String name;
    private String secondName;
    private String lastname;
    private String secondLastname;
    private String username;
    private String email; // Nuevo campo agregado
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
