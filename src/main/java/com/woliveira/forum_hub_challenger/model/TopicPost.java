package com.woliveira.forum_hub_challenger.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TopicPost")
@Table(name = "topic_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class TopicPost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String title;
    private String messenger;
    private LocalDateTime createdAt;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic_post")
    private List<TopicResponses> topicResponses = new ArrayList<>();

}
