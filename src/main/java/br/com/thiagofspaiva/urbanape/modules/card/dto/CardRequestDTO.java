package br.com.thiagofspaiva.urbanape.modules.card.dto;

import br.com.thiagofspaiva.urbanape.modules.card.models.CardEntity;
import br.com.thiagofspaiva.urbanape.modules.card.models.CardType;
import jakarta.validation.constraints.NotBlank;

import java.util.Random;
import java.util.UUID;

public record CardRequestDTO(

    @NotBlank String name,
    @NotBlank CardType type,
    boolean status,
    @NotBlank UUID user_id

) {
    public CardEntity toEntity() {
        return CardEntity.builder()
                .name(this.name)
                .type(this.type)
                .status(this.status)
                .number(gerarNumeroCartao())
                .userId(this.user_id)
                .build();
    }

    private String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            number.append(digit);
        }
        return number.toString();
    }
}
