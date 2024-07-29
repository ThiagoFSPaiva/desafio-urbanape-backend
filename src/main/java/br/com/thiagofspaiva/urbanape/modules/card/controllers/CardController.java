package br.com.thiagofspaiva.urbanape.modules.card.controllers;

import br.com.thiagofspaiva.urbanape.modules.card.dto.CardRequestDTO;
import br.com.thiagofspaiva.urbanape.modules.card.dto.CardResponseDTO;
import br.com.thiagofspaiva.urbanape.modules.card.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<Iterable<CardResponseDTO>> findAll(@RequestParam(value = "userId", required = false) UUID userId){
        return ResponseEntity.status(HttpStatus.OK).body(cardService.findAll(Optional.ofNullable(userId)));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<CardResponseDTO> create(@RequestBody CardRequestDTO card, @PathVariable UUID userId){
        return  ResponseEntity.status(HttpStatus.CREATED).body(cardService.create(card.toEntity(),userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CardResponseDTO> updateStatus(@PathVariable UUID id, @RequestParam boolean status) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cardService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
