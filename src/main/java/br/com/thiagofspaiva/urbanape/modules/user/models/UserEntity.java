package br.com.thiagofspaiva.urbanape.modules.user.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;

}
