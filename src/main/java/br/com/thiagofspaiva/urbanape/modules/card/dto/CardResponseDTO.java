package br.com.thiagofspaiva.urbanape.modules.card.dto;

import br.com.thiagofspaiva.urbanape.modules.card.models.CardEntity;
import br.com.thiagofspaiva.urbanape.modules.card.models.CardType;

import java.util.UUID;

public record CardResponseDTO(
        UUID id,
        String name,
        CardType type,

        String number,

        boolean status
) {

    public static CardResponseDTO toDto(CardEntity card) {
        return new CardResponseDTO(card.getId(), card.getName(), card.getType(), card.getNumber(), card.isStatus());
    }
}
