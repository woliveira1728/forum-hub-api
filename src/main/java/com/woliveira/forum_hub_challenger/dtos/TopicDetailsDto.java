package com.woliveira.forum_hub_challenger.dtos;

import java.util.List;
import java.util.UUID;

public record TopicDetailsDto(
        UUID id,
        String title,
        String messenger,
        UserIdDto author,
        CourseIdDto course,
        List<TopicResponsesIdDto> topicResponses,
        boolean status
) {
    public TopicDetailsDto(
            UUID id,
            String title,
            String messenger,
            UserIdDto author,
            CourseIdDto course,
            List<TopicResponsesIdDto> topicResponses,
            boolean status
    ) {
        this.id = id;
        this.title = title;
        this.messenger = messenger;
        this.author = author;
        this.course = course;
        this.topicResponses = topicResponses;
        this.status = status;
    }
}
