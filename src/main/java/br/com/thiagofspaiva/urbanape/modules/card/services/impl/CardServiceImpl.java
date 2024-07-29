package br.com.thiagofspaiva.urbanape.modules.card.services.impl;

import br.com.thiagofspaiva.urbanape.exceptions.NotFoundException;
import br.com.thiagofspaiva.urbanape.modules.card.models.CardEntity;

import br.com.thiagofspaiva.urbanape.modules.card.dto.CardResponseDTO;
import br.com.thiagofspaiva.urbanape.modules.card.repository.CardRepository;
import br.com.thiagofspaiva.urbanape.modules.card.services.CardService;

import br.com.thiagofspaiva.urbanape.modules.user.services.UserService;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;


    public CardServiceImpl(CardRepository cardRepository, UserService userService){
        this.cardRepository = cardRepository;
        this.userService = userService;
    }

    @Override
    public CardEntity findById(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));

    }
    @Override
    public Set<CardResponseDTO> findAll(Optional<UUID> userId) {
        Stream<CardEntity> cardsStream;

        if (userId.isPresent()) {
            cardsStream = cardRepository.findByUserId(userId.get()).stream();
        } else {
            cardsStream = cardRepository.findAll().stream();
        }

        return cardsStream
                .map(CardResponseDTO::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CardResponseDTO create(CardEntity card, UUID userId) { // TODO Usar entity nos parametros para não quebrar a Clean Arch
        userService.findById(userId); //TODO trocar isso por requisição quando migrar para microserviço
        card.setUserId(userId);

        return CardResponseDTO.toDto(cardRepository.save(card));

    }

    @Override
    public CardResponseDTO updateStatus(UUID id, boolean status) {
        Optional<CardEntity> card = cardRepository.findById(id);
        card.get().setStatus(status);

        return CardResponseDTO.toDto(cardRepository.save(card.get()));
    }

    @Override
    public void delete(UUID id) {
        var card = findById(id);
        cardRepository.delete(card);
    }

}
