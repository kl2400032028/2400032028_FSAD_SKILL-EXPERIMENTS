package com.skill.controller;

import com.skill.model.Course;
import com.skill.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    // GET course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: Course not found with ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    // POST - add new course
    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            return new ResponseEntity<>("Error: Course title cannot be empty", HttpStatus.BAD_REQUEST);
        }
        courseService.addCourse(course);
        return new ResponseEntity<>("Success: Course added successfully", HttpStatus.CREATED);
    }

    // PUT - update course
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody Course course) {
        boolean updated = courseService.updateCourse(id, course);
        if (updated) {
            return new ResponseEntity<>("Success: Course updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: Course not found with ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - remove course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>("Success: Course deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: Course not found with ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    // SEARCH by title
    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCoursesByTitle(@PathVariable String title) {
        List<Course> foundCourses = courseService.getCoursesByTitle(title);
        if (foundCourses.isEmpty()) {
            return new ResponseEntity<>("Error: No courses found with title matching '" + title + "'", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foundCourses, HttpStatus.OK);
        }
    }
}
