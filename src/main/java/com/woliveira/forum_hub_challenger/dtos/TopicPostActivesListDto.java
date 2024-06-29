package com.woliveira.forum_hub_challenger.dtos;

import java.util.UUID;

public record TopicPostActivesListDto(
        UUID id,
        String title,
        String messenger,
        UserIdDto author,
        CourseIdDto course
) {
    public TopicPostActivesListDto(
            UUID id,
            String title,
            String messenger,
            UserIdDto author,
            CourseIdDto course
    ) {
        this.id = id;
        this.title = title;
        this.messenger = messenger;
        this.author = author;
        this.course = course;
    }
}
