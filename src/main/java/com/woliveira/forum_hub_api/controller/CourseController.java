package com.woliveira.forum_hub_api.controller;

import com.woliveira.forum_hub_api.dtos.CourseDto;
import com.woliveira.forum_hub_api.dtos.CourseIdDto;
import com.woliveira.forum_hub_api.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping()
    @Transactional
    public ResponseEntity<String> createCourse(
            @RequestBody @Valid CourseDto courseDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        UUID courseId = courseService.saveCourse(courseDto);
        var uri = uriComponentsBuilder.path("/courses/{id}")
                .buildAndExpand(courseId)
                .toUri();

        return ResponseEntity.created(uri).body("Course created successfully");
    }

    @GetMapping()
    public ResponseEntity<Page<CourseIdDto>> cousesList(Pageable pageable) {
        Page<CourseIdDto> pageCourses = courseService.getAllCourses(pageable);

        return ResponseEntity.ok(pageCourses);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<String> updateCourse(
            @PathVariable UUID courseId, @RequestBody CourseIdDto courseIdDto
    ) {
        courseService.updateCourse(courseId, courseIdDto);

        return ResponseEntity.ok("Course updated successfully");
    }
}
