package com.woliveira.forum_hub_api.dtos;

import com.woliveira.forum_hub_api.model.TopicPost;
import com.woliveira.forum_hub_api.model.TopicResponses;
import com.woliveira.forum_hub_api.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record TopicResponsesDto(
        String messenger,
        boolean solution,
        UUID authorId,
        UUID topicId
) {
    public TopicResponses toEntity(
            User author,
            TopicPost topicPost,
            LocalDateTime createdAt
    ) {

        TopicResponses topicResponses = new TopicResponses();
        topicResponses.setMessenger(this.messenger);
        topicResponses.setSolution(this.solution);
        topicResponses.setAuthor(author);
        topicResponses.setTopicPost(topicPost);
        topicResponses.setCreatedAt(createdAt);
        return topicResponses;

    }
}
