package com.gestaohelio.api.dto;

public record CaminhaoResponseDTO(
        String placa,
        String modelo,
        String clienteNome
) {
}
