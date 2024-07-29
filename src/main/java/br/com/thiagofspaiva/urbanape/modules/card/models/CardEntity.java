package br.com.thiagofspaiva.urbanape.modules.card.models;

import br.com.thiagofspaiva.urbanape.modules.card.models.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "card")
public class CardEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CardType type;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

}
