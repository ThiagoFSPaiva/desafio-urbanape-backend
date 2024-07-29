package br.com.thiagofspaiva.urbanape.modules.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


public record TokenDTO (
        String token,
        String type,
        String issuer
){ }



