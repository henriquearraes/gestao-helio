package com.gestaohelio.api.dto;

public record AuthenticationResponseDTO(
        String login,
        String password
) {
}
