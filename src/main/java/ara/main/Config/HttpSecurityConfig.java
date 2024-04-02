package ara.main.Config;

import ara.main.Dto.util.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/auth/**",
            "/Brand/**",
            "/Product",
            "/Category/**",
            "/error",
            "/images/**",
            "/personas/register"};
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterOauth(HttpSecurity http) throws Exception {
        http.csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/Oauth/**").authenticated();
                    authConfig.requestMatchers(WHITE_LIST_URL).permitAll();
                }).oauth2Login(withDefaults());
        return http.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfig -> csrfConfig.disable())

                .sessionManagement(sessionManConfig -> sessionManConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Las sesiones ya no van a tener estados
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authConfig -> {
                    //Metodos publicos
                    //Metodos privados
                    authConfig.requestMatchers("/personas").hasAnyAuthority(Permission.SEE_ALL_USERS.name());
                    authConfig.requestMatchers("/Product/**").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    authConfig.anyRequest().authenticated();
                });

        return http.build();
    }

}