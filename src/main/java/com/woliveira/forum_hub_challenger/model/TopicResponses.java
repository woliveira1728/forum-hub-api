package com.woliveira.forum_hub_challenger.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "TopicResponses")
@Table(name = "topic_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class TopicResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String messenger;
    private LocalDateTime createdAt;
    private boolean solution;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicPost topicPost;

}