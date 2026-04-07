package com.skill.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * SKILL-4 — Annotation Configuration
 *
 * Student class configured using Spring annotations.
 * @Component marks this class as a Spring-managed bean.
 * @Value injects literal values directly — simulating property injection.
 *
 * Demonstrates Setter Injection via @Value on setter methods
 * and Constructor Injection via @Value on constructor parameters.
 */
@Component("studentBean")
public class Student {

    private int studentId;
    private String name;
    private String course;
    private int year;

    // ─── Constructor Injection via @Value ─────────────────────────────────
    public Student(
            @Value("201") int studentId,
            @Value("Priya Sharma") String name,
            @Value("Artificial Intelligence") String course,
            @Value("2") int year) {
        this.studentId = studentId;
        this.name      = name;
        this.course    = course;
        this.year      = year;
        System.out.println("  [Annotation] Constructor Injection triggered.");
    }

    // ─── Setter Injection via @Value ─────────────────────────────────────
    // Uncommenting these setters and commenting the constructor will demonstrate
    // pure setter injection via annotations.

    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setName(String name)        { this.name = name; }

    @Value("Data Science & ML")
    public void setCourse(String course) {
        // This setter is called AFTER constructor, overriding the course value
        this.course = course;
        System.out.println("  [Annotation] Setter Injection → course set to: " + course);
    }

    @Value("3")
    public void setYear(int year) {
        // This setter is called AFTER constructor, overriding year
        this.year = year;
        System.out.println("  [Annotation] Setter Injection → year  set to: " + year);
    }

    // ─── Getters ──────────────────────────────────────────────────────────
    public int    getStudentId() { return studentId; }
    public String getName()      { return name; }
    public String getCourse()    { return course; }
    public int    getYear()      { return year; }

    // ─── Display ──────────────────────────────────────────────────────────
    public void display() {
        System.out.println("  ┌─────────────────────────────────────────────┐");
        System.out.printf ("  │  Student ID  : %-28d │%n", studentId);
        System.out.printf ("  │  Name        : %-28s │%n", name);
        System.out.printf ("  │  Course      : %-28s │%n", course);
        System.out.printf ("  │  Year        : %-28d │%n", year);
        System.out.println("  └─────────────────────────────────────────────┘");
    }
}
