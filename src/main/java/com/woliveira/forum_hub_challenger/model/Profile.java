package com.woliveira.forum_hub_challenger.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Profile")
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "profiles")
    private Set<User> users = new HashSet<>();

}
