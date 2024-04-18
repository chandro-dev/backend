package ara.main.Dto;

import ara.main.Dto.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PersonsDto {
    private String identification;
    private String name;
    private String lastname;
    private String email;
}
