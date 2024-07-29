package br.com.thiagofspaiva.urbanape.modules.card.repository;

import br.com.thiagofspaiva.urbanape.modules.card.models.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    List<CardEntity> findByUserId(UUID userId);
}
