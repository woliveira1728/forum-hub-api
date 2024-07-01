package com.woliveira.forum_hub_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "TopicPost")
@Table(name = "topic_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicPost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String messenger;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topicPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TopicResponses> topicResponses = new ArrayList<>();

}
