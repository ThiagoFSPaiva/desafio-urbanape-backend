package br.com.thiagofspaiva.urbanape.modules.user.controllers;


import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.dto.UserResponseDTO;
import br.com.thiagofspaiva.urbanape.modules.user.dto.UserRequestDTO;
import br.com.thiagofspaiva.urbanape.modules.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body( userService.create(user.toEntity()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id, @RequestBody UserRequestDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,user.toEntity()));
    }

    @GetMapping
    public ResponseEntity<Iterable<UserResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
