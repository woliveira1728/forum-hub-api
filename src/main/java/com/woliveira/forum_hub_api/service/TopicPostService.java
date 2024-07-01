package com.woliveira.forum_hub_api.service;

import com.woliveira.forum_hub_api.dtos.*;
import com.woliveira.forum_hub_api.model.Course;
import com.woliveira.forum_hub_api.model.TopicPost;
import com.woliveira.forum_hub_api.model.User;
import com.woliveira.forum_hub_api.repository.TopicPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TopicPostService {

    @Autowired
    private TopicPostRepository topicPostRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Transactional
    public UUID saveTopicPost(TopicPostDto topicPostDto, String userEmail) {
        if (topicPostDto.title() == null || topicPostDto.messenger() == null) {
            throw new IllegalArgumentException("Title and/or message is required");
        }

        User newUser = userService.findByEmail(userEmail);
        if (topicPostRepository.existsByTitleAndMessengerAndCourseId(
                topicPostDto.title(), topicPostDto.messenger(), topicPostDto.course().id()
        )) {
            throw new IllegalArgumentException("Topic already exist");
        }

        UUID courseId = topicPostDto.course().id();
        if (courseId == null) {
            throw new IllegalArgumentException("Invalid courseId");
        }

        TopicPost topicPost = new TopicPost();
        topicPost.setTitle(topicPostDto.title());
        topicPost.setMessenger(topicPostDto.messenger());
        topicPost.setAuthor(newUser);
        topicPost.setCourse(new Course(courseId, topicPostDto.course().name(), topicPostDto.course().category()));
        topicPost.setCreatedAt(LocalDateTime.now());
        topicPost.setStatus(true);
        TopicPost newTopic = topicPostRepository.save(topicPost);

        return newTopic.getId();
    }

    public Page<TopicPostListDto> getAllTopicsByNameAndYear(
            Pageable pageable, String courseName, Integer year
    ) {
        Page<TopicPost> pageTopics;
        if (courseName != null && year != null) {
            pageTopics = topicPostRepository.findByCourseNameAndYear(courseName, year, pageable);
        } else {
            pageTopics = topicPostRepository.findAllByOrderCreatedAt(pageable);
        }

        return pageTopics.map(topic -> new TopicPostListDto(
                topic.getId(),
                topic.getTitle(),
                topic.getMessenger(),
                new UserIdDto(topic.getAuthor().getId(), topic.getAuthor().getName(), topic.getAuthor().getEmail()),
                new CourseIdDto(topic.getCourse().getId(), topic.getCourse().getName(),
                        topic.getCourse().getCategory()),
                topic.isStatus()
        ));
    }

    public void updteUser(UUID topicId, TopicDetailsDto topicDetailsDto) {
        Optional<TopicPost> optionalTopicPost = topicPostRepository.findById(topicId);
        if (optionalTopicPost.isEmpty()) {
            throw new IllegalStateException("Topic not found");
        }

        TopicPost newTopic = optionalTopicPost.get();
        newTopic.setTitle(topicDetailsDto.title());
        newTopic.setMessenger(topicDetailsDto.messenger());
        try {
            topicPostRepository.save(newTopic);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Title or message is required", e);
        }
    }

    @Transactional
    public void inactivateTopic(UUID id) {
        TopicPost topicPost = topicPostRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Topic not found"));
        topicPost.setStatus(false);

        topicPostRepository.save(topicPost);
    }

    public Page<TopicPostActivesListDto> getAllTopicActives(Pageable pageable, String courseName, Integer year) {
        Page<TopicPost> pageTopics;
        pageTopics = topicPostRepository.findByStatusTrue(pageable);

        return pageTopics.map(topic -> new TopicPostActivesListDto(
                topic.getId(),
                topic.getTitle(),
                topic.getMessenger(),
                new UserIdDto(topic.getAuthor().getId(), topic.getAuthor().getName(), topic.getAuthor().getEmail()),
                new CourseIdDto(topic.getCourse().getId(), topic.getCourse().getName(), topic.getCourse().getCategory())
        ));
    }

    public TopicPost findById(UUID id) {
        return topicPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
    }

    public Optional<TopicDetailsDto> topicDetails(UUID id) {
        Optional<TopicPost> optionalTopic = topicPostRepository.findByIdAndStatusTrue(id);
        return optionalTopic.map(this::mapToTopicDetails);
    }

    private TopicDetailsDto mapToTopicDetails(TopicPost topicPost) {
        List<TopicResponsesIdDto> responsesDto = topicPost.getTopicResponses().stream()
                .map(response -> new TopicResponsesIdDto(
                        response.getId(),
                        response.getMessenger(),
                        response.getCreatedAt(),
                        response.isSolution(),
                        response.getAuthor().getId(),
                        response.getTopicPost().getId()
                ))
                .collect(Collectors.toList());

        return new TopicDetailsDto(
                topicPost.getId(),
                topicPost.getTitle(),
                topicPost.getMessenger(),
                new UserIdDto(topicPost.getAuthor().getId(), topicPost.getAuthor().getName(), topicPost.getAuthor().getEmail()),
                new CourseIdDto(topicPost.getCourse().getId(), topicPost.getCourse().getName(),
                        topicPost.getCourse().getCategory()),
                responsesDto,
                topicPost.isStatus()
        );
    }
}
