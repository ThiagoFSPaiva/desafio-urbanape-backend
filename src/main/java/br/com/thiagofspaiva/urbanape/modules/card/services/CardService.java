package br.com.thiagofspaiva.urbanape.modules.card.services;

import br.com.thiagofspaiva.urbanape.modules.card.models.CardEntity;
import br.com.thiagofspaiva.urbanape.modules.card.dto.CardResponseDTO;

import java.util.Optional;
import java.util.UUID;

public interface CardService {
    CardEntity findById(UUID id);
    Iterable<CardResponseDTO> findAll(Optional<UUID> userId);

    CardResponseDTO create(CardEntity card, UUID userId);

    CardResponseDTO updateStatus(UUID id, boolean status);

    void delete(UUID id);
}
