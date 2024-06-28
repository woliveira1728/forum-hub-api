package com.woliveira.forum_hub_challenger.dtos;

import com.woliveira.forum_hub_challenger.model.TopicPost;
import com.woliveira.forum_hub_challenger.model.TopicResponses;
import com.woliveira.forum_hub_challenger.model.User;

import java.time.LocalDateTime;

public record TopicResponsesDto(
        String messenger,
        boolean solution,
        Long authorId,
        Long topicId
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
