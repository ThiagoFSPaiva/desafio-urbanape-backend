package br.com.thiagofspaiva.urbanape.modules.user.dto;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.models.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    @NotBlank String name,
    @NotBlank  String password,
    @Email String email

){

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(this.name)
                .password(this.password)
                .email(this.email)
                .type(UserType.USER)
                .build();
    }
}
