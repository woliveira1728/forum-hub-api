package com.woliveira.forum_hub_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record CourseDto(
        @NotBlank String name,
        @NotBlank String category
) {
}
