package br.com.thiagofspaiva.urbanape.modules.user.dto;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.models.UserType;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,

        UserType type

){
    public static UserResponseDTO ToDto(UserEntity entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getType()
        );
    }
}
