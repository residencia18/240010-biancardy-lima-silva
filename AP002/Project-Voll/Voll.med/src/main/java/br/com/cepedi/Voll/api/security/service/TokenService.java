package br.com.cepedi.Voll.api.security.service;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TokenService {

    @Value("${api.security.toke.secret}")
    private String secret;

    private static final String ISSUER = "API Voll.med";

    private final Set<String> revokedTokens = new HashSet<>();

    public void revokeToken(String token) {
        revokedTokens.add(token);
    }

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withClaim("email",user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    public String generateTokenRecoverPassword(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withClaim("email",user.getEmail())
                    .withExpiresAt(expirationDateRecoverPassword())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    public boolean isValidToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);
            return !revokedTokens.contains(token);
        } catch (JWTVerificationException exception) {
            return false;
        }
    }



    private Instant expirationDate () {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant expirationDateRecoverPassword () {
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getEmailByToken(String token) {
        return JWT.decode(token).getClaim("email").asString();
    }


    public String getSubject(String tokenJWT) throws JWTVerificationException {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token JWT inválido ou expirado!");
        }
    }
}
