package com.woliveira.forum_hub_api.dtos;

import java.util.UUID;

public record UserIdDto(
        UUID id,
        String name,
        String email,
        boolean status
) {
    public UserIdDto(UUID id, String name, String email) {
        this(id, name, email, false);
    }
}
