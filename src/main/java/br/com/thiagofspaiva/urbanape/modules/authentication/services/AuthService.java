package br.com.thiagofspaiva.urbanape.modules.authentication.services;

import br.com.thiagofspaiva.urbanape.exceptions.NotFoundException;
import br.com.thiagofspaiva.urbanape.modules.authentication.dto.AuthDTO;
import br.com.thiagofspaiva.urbanape.modules.authentication.dto.TokenDTO;
import br.com.thiagofspaiva.urbanape.modules.user.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthService {

    @Value("${security.token.secret}")
    private String secretKey;
    final private UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDTO authenticate(AuthDTO authDTO) {

            var user = userRepository.findByEmail(authDTO.getEmail())
                    .orElseThrow(() -> new NotFoundException("Email ou senha incorreta"));

            var passswordMatches = passwordEncoder.matches(authDTO.getPassword(), user.getPassword());

            if (!passswordMatches) {
                throw new NotFoundException("Email ou senha incorreta");
            }

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            var token = JWT.create()
                    .withSubject(user.getId().toString())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algorithm);

            return TokenDTO.builder().token(token).type("Bearer").build();

    }
}
