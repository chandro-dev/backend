package ara.main.Config;

import ara.main.Entity.personas.Persons;
import ara.main.Repository.PersonasRepository.PersonRepository;

import ara.main.Service.JwtService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private PersonRepository personRepository;
    @Autowired

    @Qualifier("firstEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. Obtener el Header que contiene el JWT
        String authHeader = request.getHeader("Authorization"); //Saca el bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //2. Obtener el jwt desde el header
        String jwt = authHeader.split(" ")[1];

        //3. Obtener el username desde el jwt
        String username = jwtService.extractUsername(jwt);

        //4. Setear un objeto dentro del SecurityContext
        Persons user = personRepository.findByUsername(username).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //5. Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
