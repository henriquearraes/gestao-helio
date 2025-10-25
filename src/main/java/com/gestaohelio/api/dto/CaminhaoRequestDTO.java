package com.gestaohelio.api.dto;

public record CaminhaoRequestDTO(
        String placa,
        String modelo,
        Long clienteId
) {
}
