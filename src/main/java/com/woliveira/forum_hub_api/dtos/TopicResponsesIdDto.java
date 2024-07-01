package com.woliveira.forum_hub_api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record TopicResponsesIdDto(
        UUID id,
        String messenger,
        LocalDateTime createdAt,
        boolean solution,
        UUID authorId,
        UUID topicId
) {
}
