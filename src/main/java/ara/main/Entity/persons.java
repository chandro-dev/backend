package ara.main.Entity;


import ara.main.Dto.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class persons implements UserDetails {
    @Id
    @Getter
    @Setter
    private String identification;
    @Getter
    @Setter
    private String name;
    @Column(name = "second_name")
    @Getter
    @Setter


    private String secondName;
    @Getter
    @Setter

    private String lastname;
    @Getter
    @Setter
    @Column(name = "second_lastname")
    private String secondLastname;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email; // Nuevo campo agregado
    @Setter
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
