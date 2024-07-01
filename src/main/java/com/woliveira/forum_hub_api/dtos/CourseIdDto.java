package com.woliveira.forum_hub_api.dtos;

import java.util.UUID;

public record CourseIdDto(
        UUID id,
        String name,
        String category
) {
}
