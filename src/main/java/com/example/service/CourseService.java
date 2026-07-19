package com.example.service;

import com.example.dto.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    // Create a new course
    public void addCourse(Course course) {
        log.info("Inside addCourse method");
        courses.add(course);
        log.info("{} course added succesfully");
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        log.info("inside getAllCourses method");
        return courses;
    }

    // Retrieve a course by id
    public Optional<Course> getCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    // Update a course
    public boolean updateCourse(int id, Course newCourse) {
        return getCourseById(id).map(existingCourse -> {
            courses.remove(existingCourse);
            courses.add(newCourse);
            return true;
        }).orElse(false);
    }

    // Delete a course by id
    public boolean deleteCourse(int id) {
        return courses
                .removeIf(course -> course.getId() == id);
    }
}
