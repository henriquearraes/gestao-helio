package com.gestaohelio.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServicoResponseDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        String status,
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida,
        String caminhaoPlaca,
        String funcionarioNome
) {
}
