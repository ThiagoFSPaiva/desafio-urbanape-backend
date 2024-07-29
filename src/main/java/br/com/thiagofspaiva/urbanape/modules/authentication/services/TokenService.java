package br.com.thiagofspaiva.urbanape.modules.authentication.services;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.token.secret}")
    private String secretKey;
    public String generateToken(UserEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("urbanape-backend")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return token;

        }catch (JWTCreationException e) {
            throw new RuntimeException("Error ao gerar o token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("urbanape-backend")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
