package yas.dio.product_trial.infrastructure.out;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import yas.dio.product_trial.application.ports.out.TokenProvider;

import java.util.Date;
import java.util.Map;

@Component
public class JwtService implements TokenProvider {

    private static final String SECRET = "secret";

    @Override
    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), Jwts.SIG.HS256)
                .compact();
    }
}
