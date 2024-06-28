package com.woliveira.forum_hub_challenger.repository;

import com.woliveira.forum_hub_challenger.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
