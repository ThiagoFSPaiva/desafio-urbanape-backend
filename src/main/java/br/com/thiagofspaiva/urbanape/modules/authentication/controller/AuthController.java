package br.com.thiagofspaiva.urbanape.modules.authentication.controller;

import br.com.thiagofspaiva.urbanape.modules.authentication.dto.AuthDTO;
import br.com.thiagofspaiva.urbanape.modules.authentication.dto.TokenDTO;
import br.com.thiagofspaiva.urbanape.modules.authentication.services.AuthService;
import br.com.thiagofspaiva.urbanape.modules.authentication.services.TokenService;
import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final private AuthService authService;
    final private AuthenticationManager authenticationManager;

    final private TokenService tokenService;

    AuthController(AuthService authService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping()
    public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO(token,"Bearer", "urbanape-backend"));
    }
}
