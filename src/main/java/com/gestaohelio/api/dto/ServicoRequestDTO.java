package com.gestaohelio.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServicoRequestDTO(
        String descricao,
        BigDecimal valor,
        String status,
        LocalDateTime dataSaida,
        Long caminhaoId,
        Long funcionarioId
) {
}
