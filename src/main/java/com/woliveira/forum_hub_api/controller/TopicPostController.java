package com.woliveira.forum_hub_api.controller;

import com.woliveira.forum_hub_api.dtos.*;
import com.woliveira.forum_hub_api.model.User;
import com.woliveira.forum_hub_api.service.TopicPostService;
import com.woliveira.forum_hub_api.service.TopicResponsesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicPostController {

    @Autowired
    private TopicPostService topicPostService;

    @Autowired
    private TopicResponsesService topicResponsesService;

    @PostMapping()
    public ResponseEntity<String> createTopic(
            @RequestBody @Valid TopicPostDto topicPostDto,
            UriComponentsBuilder uriComponentsBuilder, Authentication authentication
    ) {

        String isUserEmail = authentication.getName();
        UUID topicId = topicPostService.saveTopicPost(topicPostDto, isUserEmail);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicId).toUri();

        return ResponseEntity.created(uri).body("Topic created successfully");

    }

    @GetMapping("/list")
    public ResponseEntity<Page<TopicPostActivesListDto>> topicPostActiveList(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TopicPostActivesListDto> pageTopics = topicPostService.getAllTopicActives(pageable, courseName, year);

        return ResponseEntity.ok(pageTopics);
    }

    @GetMapping("/list-all-to-admin")
    public ResponseEntity<Page<TopicPostListDto>> topicPostAllList(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TopicPostListDto> pageTopics = topicPostService.getAllTopicsByNameAndYear(pageable, courseName, year);

        return ResponseEntity.ok(pageTopics);
    }

    @GetMapping("/{id}")
    public ResponseEntity topicDestails(@PathVariable UUID id) {
        Optional<TopicDetailsDto> optionalTopicDetailsDto = topicPostService.topicDetails(id);

        return optionalTopicDetailsDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<String> updateTopicPost(
            @PathVariable UUID topicId, @RequestBody TopicDetailsDto topicDetailsDto
    ) {
        topicPostService.updteUser(topicId, topicDetailsDto);

        return ResponseEntity.ok("Topic updated successfully");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable UUID id) {

        topicPostService.inactivateTopic(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/responses/{topicId}")
    public ResponseEntity<?> saveTopicResponse(
            @PathVariable UUID topicId,
            @RequestBody TopicResponsesDto topicResponsesDto,
            Principal principal
    ) {

        User author = topicResponsesService.findByEmail(principal.getName());
        LocalDateTime createdAt = LocalDateTime.now();
        topicResponsesService.saveResponse(topicId, topicResponsesDto, author, createdAt);

        return ResponseEntity.ok().build();
    }
}
