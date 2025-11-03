package com.gestaohelio.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
        String nome,
        @NotBlank(message = "O telefone é obrigatório.")
        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
        String telefone,
        @Email(message = "E-mail inválido.")
        @Size(max = 120)
        String email,
        @Size(max = 18, message = "O CPF/CNPJ deve ter no máximo 18 caracteres.")
        String cpfCnpj
) {
}
