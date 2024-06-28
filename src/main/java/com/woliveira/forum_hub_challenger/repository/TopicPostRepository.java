package com.woliveira.forum_hub_challenger.repository;

import com.woliveira.forum_hub_challenger.model.TopicPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicPostRepository extends JpaRepository<TopicPost, Long> {

    boolean existsByTitle(String title);

    boolean existsByMessenger(String messenger);

    @Query("SELECT t FROM TopicPost t WHERE t.course.name = :courseName AND FUNCTION('YEAR', t.created_at) =year")
    Page<TopicPost> findByCourseNameAndYear(String courseName, int year, Pageable pageable);

    Optional<TopicPost> findByIdAndStatusTrue(Long id);

}
