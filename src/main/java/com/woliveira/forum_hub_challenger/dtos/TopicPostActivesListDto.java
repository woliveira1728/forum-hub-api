package com.woliveira.forum_hub_challenger.dtos;

public record TopicPostActivesListDto(
        Long id,
        String title,
        String messenger,
        UserIdDto author,
        CourseIdDto course
) {
    public TopicPostActivesListDto(
        Long id,
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
