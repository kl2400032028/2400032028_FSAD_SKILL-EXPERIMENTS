package com.skill.controller;

import com.skill.model.Student;
import com.skill.exception.StudentNotFoundException;
import com.skill.exception.InvalidInputException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    private static Map<Integer, Student> students = new HashMap<>();

    static {
        students.put(1, new Student(1, "Alice", "Computer Science"));
        students.put(2, new Student(2, "Bob", "Information Technology"));
        students.put(3, new Student(3, "Charlie", "Cyber Security"));
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable String id) {
        int studentId;
        try {
            studentId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a numeric value.");
        }

        if (studentId <= 0) {
            throw new InvalidInputException("Student ID must be a positive integer.");
        }

        if (!students.containsKey(studentId)) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
        }

        return students.get(studentId);
    }
}
