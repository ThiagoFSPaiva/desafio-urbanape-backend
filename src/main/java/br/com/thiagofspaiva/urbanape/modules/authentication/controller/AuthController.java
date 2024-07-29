package br.com.thiagofspaiva.urbanape.modules.authentication.controller;

import br.com.thiagofspaiva.urbanape.modules.authentication.dto.AuthDTO;
import br.com.thiagofspaiva.urbanape.modules.authentication.dto.TokenDTO;
import br.com.thiagofspaiva.urbanape.modules.authentication.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final private AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(authDTO));
    }
}
