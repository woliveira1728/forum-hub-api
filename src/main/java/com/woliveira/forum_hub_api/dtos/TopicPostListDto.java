package com.woliveira.forum_hub_api.dtos;

import java.util.UUID;

public record TopicPostListDto(
        UUID id,
        String title,
        String messenger,
        UserIdDto author,
        CourseIdDto course,
        boolean status
) {
    public TopicPostListDto(
            UUID id,
            String title,
            String messenger,
            UserIdDto author,
            CourseIdDto course,
            boolean status
    ) {
        this.id = id;
        this.title = title;
        this.messenger = messenger;
        this.author = author;
        this.course = course;
        this.status = status;
    }
}
