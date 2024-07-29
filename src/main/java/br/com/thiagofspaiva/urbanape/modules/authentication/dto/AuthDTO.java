package br.com.thiagofspaiva.urbanape.modules.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {
    private String email;
    private String password;
}
