package ara.main.Service;

import ara.main.Entity.persons;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;
    public String generateToken(persons persons, Map<String, Object> extraClaims) {

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

    public String extractID(String jwt) {
        return getBody(jwt).get("id").toString();
    }

    private Claims getBody(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private String extractName(String token){
        Claims claims= getBody(token);
        return claims.get("name").toString();
    }
    public ResponseEntity<Boolean> isTokenValid(String token) {

        try{
            final String username = extractID(token);
            if (username != null ) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        }
    }

    public ResponseEntity<Boolean> isTokenValidWithGoogle(String token) {
        try{final String username = extractID(token);

            if (username != null ) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getBody(token);
        return claimsResolver.apply(claims);
    }

}
