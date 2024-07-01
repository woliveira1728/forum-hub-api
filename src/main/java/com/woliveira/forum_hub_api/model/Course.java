package com.woliveira.forum_hub_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Course")
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String category;

    @OneToMany(mappedBy = "course")
    private List<TopicPost> topicsPosts = new ArrayList<>();

    public Course(UUID id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

}
