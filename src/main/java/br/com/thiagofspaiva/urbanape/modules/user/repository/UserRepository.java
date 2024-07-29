package br.com.thiagofspaiva.urbanape.modules.user.repository;

import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findByEmail(String email);
    Optional<UserEntity> findById(UUID id);

}
