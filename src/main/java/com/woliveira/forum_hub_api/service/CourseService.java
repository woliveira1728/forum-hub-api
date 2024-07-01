package com.woliveira.forum_hub_api.service;

import com.woliveira.forum_hub_api.dtos.CourseDto;
import com.woliveira.forum_hub_api.dtos.CourseIdDto;
import com.woliveira.forum_hub_api.model.Course;
import com.woliveira.forum_hub_api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public UUID saveCourse(CourseDto courseDto) {
        if (courseDto.name() == null || courseDto.category() == null) {
            throw new IllegalArgumentException("Name and/or category fields are required.");
        }

        Course course = new Course();
        course.setName(courseDto.name());
        course.setCategory(courseDto.category());

        Course newCourse = courseRepository.save(course);

        return newCourse.getId();
    }

    public Page<CourseIdDto> getAllCourses(Pageable pageable) {
        Page<Course> pageCourses = courseRepository.findAll(pageable);

        return pageCourses.map(course -> new CourseIdDto(
                course.getId(), course.getName(), course.getCategory()
        ));
    }

    public void updateCourse(UUID courseId, CourseIdDto courseIdDto) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()) {
            throw new IllegalStateException("Course not found");
        }
        Course isCourse = optionalCourse.get();
        isCourse.setName(courseIdDto.name());
        isCourse.setCategory(courseIdDto.category());
        courseRepository.save(isCourse);
    }

    public boolean existsById(UUID courseId) {
        return courseRepository.existsById(courseId);
    }

}
