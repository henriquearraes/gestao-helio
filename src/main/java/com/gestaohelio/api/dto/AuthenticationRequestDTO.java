package com.gestaohelio.api.dto;

import com.gestaohelio.domain.user.UserRole;

public record AuthenticationRequestDTO(
        String login,
        String password,
        UserRole role
) {
}
