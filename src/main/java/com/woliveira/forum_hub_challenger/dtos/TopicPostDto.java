package com.woliveira.forum_hub_challenger.dtos;

import jakarta.validation.constraints.NotBlank;

public record TopicPostDto(
        @NotBlank String title,
        @NotBlank String messenger,
        UserIdDto author,
        CourseIdDto course
) {
}
