package ara.main.Config;

import ara.main.Entity.persons;
import ara.main.Repositories.PersonRepository;
import ara.main.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final PersonRepository personRepository;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. Obtener el Header que contiene el JWT
        String authHeader=request.getHeader("Authorization"); //Saca el bearer
        if (authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //2. Obtener el jwt desde el header
        String jwt = authHeader.split(" ")[1];

        //3. Obtener el username desde el jwt
        String id=jwtService.extractID(jwt);

        //4. Setear un objeto dentro del SecurityContext
        persons user= personRepository.findById(id).get();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                user.getUsername(), null,user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //5. Ejecutar el resto de filtros
        filterChain.doFilter(request,response);
    }
}
