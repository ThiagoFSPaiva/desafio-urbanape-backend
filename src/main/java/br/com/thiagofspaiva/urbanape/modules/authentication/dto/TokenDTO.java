package br.com.thiagofspaiva.urbanape.modules.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TokenDTO {
    private String token;
    private String type;
}
