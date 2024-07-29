package br.com.thiagofspaiva.urbanape.modules.user.dto;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    @NotBlank String name,
    @NotBlank  String password,
    @Email String email,

    UserRole role

){

    public UserEntity toEntity() {

        UserRole finalRole = this.role != null ? this.role : UserRole.USER;

        return UserEntity.builder()
                .name(this.name)
                .password(this.password)
                .email(this.email)
                .role(finalRole)
                .build();
    }
}
