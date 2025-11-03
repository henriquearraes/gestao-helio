package com.gestaohelio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioRequestDTO(
        @NotBlank(message = "O nome do funcionário é obrigatório.")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
        String nome,
        @NotBlank(message = "O cargo é obrigatório.")
        @Size(max = 60, message = "O cargo deve ter no máximo 60 caracteres.")
        String cargo
) {
}
