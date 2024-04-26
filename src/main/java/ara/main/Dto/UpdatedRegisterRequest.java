package ara.main.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedRegisterRequest {
    private String identification;
    private String username;
    private String dni;
    private String email;
}
