package com.woliveira.forum_hub_challenger.dtos;

import java.util.UUID;

public record UserDetailsDto(
        UUID id,
        String name,
        String email,
        boolean status
) {
}
