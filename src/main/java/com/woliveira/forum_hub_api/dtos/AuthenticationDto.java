package com.woliveira.forum_hub_api.dtos;

public record AuthenticationDto(
        String login,
        String password
) {
}
