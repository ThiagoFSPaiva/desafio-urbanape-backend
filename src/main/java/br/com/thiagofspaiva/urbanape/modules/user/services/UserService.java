package br.com.thiagofspaiva.urbanape.modules.user.services;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.dto.UserResponseDTO;

import java.util.UUID;

public interface UserService {

    UserEntity findById(UUID id);
    Iterable<UserResponseDTO> findAll();

    UserResponseDTO create(UserEntity user);

    UserResponseDTO update(UUID id, UserEntity user);

    void delete(UUID id);

}
