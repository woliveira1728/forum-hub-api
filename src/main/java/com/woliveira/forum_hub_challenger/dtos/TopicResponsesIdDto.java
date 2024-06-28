package com.woliveira.forum_hub_challenger.dtos;

import java.time.LocalDateTime;

public record TopicResponsesIdDto(
        Long id,
        String messenger,
        LocalDateTime createdAt,
        boolean solution,
        Long authorId,
        Long topicId
) {
}
