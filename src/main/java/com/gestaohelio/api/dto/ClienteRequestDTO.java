package com.gestaohelio.api.dto;

public record ClienteRequestDTO(
        String nome,
        String telefone,
        String email,
        String cpfCnpj
) {
}
