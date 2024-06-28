package com.woliveira.forum_hub_challenger.dtos;

import java.util.List;

public record TopicDetailsDto(
        Long id,
        String title,
        String messenger,
        UserIdDto author,
        CourseIdDto course,
        List<TopicResponsesIdDto> topicResponses,
        boolean status
) {
    public TopicDetailsDto(
            Long id,
            String title,
            String messenger,
            UserIdDto author,
            CourseIdDto course,
            List<TopicResponsesIdDto> topicResponses,
            boolean status
    ){
        this.id = id;
        this.title = title;
        this. messenger = messenger;
        this.author = author;
        this.course = course;
        this.topicResponses = topicResponses;
        this.status = status;
    }
}
