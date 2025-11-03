package com.gestaohelio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServicoRequestDTO(
        @NotBlank(message ="A descrição do serviço é obrigatório.")
        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao,
        @NotNull(message = "O valor do serviço é obrigatório")
        @Positive(message = "O valor precisa ser positivo")
        BigDecimal valor,
        String status,
        LocalDateTime dataSaida,
        Long caminhaoId,
        Long funcionarioId
) {
}
