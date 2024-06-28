package com.woliveira.forum_hub_challenger.dtos;

public record UserIdDto(
        Long id,
        String name,
        String email,
        boolean status
) {
    public UserIdDto(Long id, String name, String email) {
        this(id, name, email, false);
    }
}
