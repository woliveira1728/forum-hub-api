package com.woliveira.forum_hub_challenger.repository;

import com.woliveira.forum_hub_challenger.model.TopicPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicPostRepository extends JpaRepository<TopicPost, UUID> {

    boolean existsByTitle(String title);

    boolean existsByMessenger(String messenger);

    //    @Query("SELECT t FROM TopicPost t WHERE t.course.name = :courseName AND FUNCTION('YEAR', t.created_at) = :year")
    @Query("SELECT t FROM TopicPost t WHERE t.course.name = :courseName AND YEAR(t.createdAt) = :year")
    Page<TopicPost> findByCourseNameAndYear(String courseName, int year, Pageable pageable);

    Optional<TopicPost> findByIdAndStatusTrue(UUID id);

    boolean existsByTitleAndMessengerAndCourseId(String title, String messenger, UUID courseId);

    @Query("SELECT t FROM TopicPost t ORDER BY t.createdAt ASC")
    Page<TopicPost> findAllByOrderCreatedAt(Pageable pageable);

    Page<TopicPost> findByStatusTrue(Pageable pageable);
}
