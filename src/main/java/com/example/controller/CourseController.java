package com.example.controller;

import com.example.dto.Course;
import com.example.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taxcredits/mservices/admin/course")
public class CourseController {

    Logger log= LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;


    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        log.info("Inside POST Call : Adding a Ccourse {}",course);
        courseService.addCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourses() {
        log.info("Inside GET call of getAllCourses");
        List<Course> courses = courseService.getAllCourses();
        log.info("Response for getAllCourses {}",courses);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course newCourse) {
        boolean updated = courseService.updateCourse(id, newCourse);
        if (updated) {
            return new ResponseEntity<>(newCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
