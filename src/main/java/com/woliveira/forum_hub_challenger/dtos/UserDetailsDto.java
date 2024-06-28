package com.woliveira.forum_hub_challenger.dtos;

public record UserDetailsDto(
        Long id,
        String name,
        String email,
        boolean status
) {
}
