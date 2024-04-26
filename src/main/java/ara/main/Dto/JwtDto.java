package ara.main.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtDto {
    private String token;
    private String username;
}
