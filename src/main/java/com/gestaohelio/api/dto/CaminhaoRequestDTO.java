package com.gestaohelio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CaminhaoRequestDTO(
        @NotBlank(message = "A placa é obrigatória.")
        @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres.")
        String placa,
        @NotBlank(message = "O modelo é obrigatório.")
        @Size(max = 60, message = "O modelo deve ter no máximo 60 caracteres.")
        String modelo,
        Long clienteId
) {
}
