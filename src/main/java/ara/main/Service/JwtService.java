package ara.main.Service;

import ara.main.Entity.personas.Persons;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;
    public String generateToken(Persons persons, Map<String, Object> extraClaims) {

        Date issuetAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuetAt.getTime()+(EXPIRATION_MINUTES * 60 * 1000));

        String jwt= Jwts.builder()
                //Header
                .header()
                .type("JWT")
                .and()
                //Payload
                .claims(extraClaims)
                .subject(persons.getUsername())
                .issuedAt(issuetAt)
                .expiration(expiration)

                //Firma
                .signWith(generateKey())
                .compact();
        return jwt;
    }

    private SecretKey generateKey(){
        byte[] secretAsByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsByte);
    }

    public String extractUsername(String jwt) {
        return getBody(jwt).getSubject();
    }

    private Claims getBody(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
