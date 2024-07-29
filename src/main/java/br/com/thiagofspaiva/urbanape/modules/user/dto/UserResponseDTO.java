package br.com.thiagofspaiva.urbanape.modules.user.dto;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.models.UserRole;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,

        UserRole role

){
    public static UserResponseDTO ToDto(UserEntity entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getRole()
        );
    }
}
