package ara.main.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class RegisterGoogleResponse {
    private String message;
    private String idGoogle;
}
