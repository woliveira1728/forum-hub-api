package com.woliveira.forum_hub_challenger.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "TopicResponses")
@Table(name = "topic_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String messenger;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private boolean solution;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "topic_post_id")
    private TopicPost topicPost;

}