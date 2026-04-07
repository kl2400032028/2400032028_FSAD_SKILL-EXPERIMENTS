package com.skill.service;

import com.skill.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public CourseService() {
        courses.add(new Course(101, "Java Full Stack", "6 months", 5000.0));
        courses.add(new Course(102, "Python Data Science", "4 months", 4500.0));
        courses.add(new Course(103, "Spring Boot Microservices", "3 months", 4000.0));
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Optional<Course> getCourseById(int id) {
        return courses.stream().filter(c -> c.getCourseId() == id).findFirst();
    }

    public List<Course> getCoursesByTitle(String title) {
        return courses.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean updateCourse(int id, Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId() == id) {
                updatedCourse.setCourseId(id);
                courses.set(i, updatedCourse);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(int id) {
        return courses.removeIf(c -> c.getCourseId() == id);
    }
}
