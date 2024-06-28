package com.woliveira.forum_hub_challenger.dtos;

public record TopicPostListDto(
        Long id,
        String title,
        String messenger,
        UserIdDto author,
        CourseIdDto course,
        boolean status
) {
    public TopicPostListDto(
            Long id,
            String title,
            String messenger,
            UserIdDto author,
            CourseIdDto course,
            boolean status
    ){
        this.id = id;
        this.title = title;
        this.messenger = messenger;
        this.author = author;
        this.course = course;
        this.status = status;
    }
}
