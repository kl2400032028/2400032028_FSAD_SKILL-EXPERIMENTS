package com.skill.xml;

/**
 * SKILL-4 — XML Configuration
 *
 * Student POJO class used with Spring XML-based bean configuration.
 * Demonstrates:
 *   - Constructor Injection (all 4 fields via <constructor-arg>)
 *   - Setter Injection (course and year via <property>)
 */
public class Student {

    private int studentId;
    private String name;
    private String course;
    private int year;

    // ─── Default Constructor (required for Setter Injection) ───────────────
    public Student() {}

    // ─── Parameterized Constructor (for Constructor Injection) ────────────
    public Student(int studentId, String name, String course, int year) {
        this.studentId = studentId;
        this.name      = name;
        this.course    = course;
        this.year      = year;
    }

    // ─── Setters (for Setter Injection) ───────────────────────────────────
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setName(String name)        { this.name = name; }
    public void setCourse(String course)    { this.course = course; }
    public void setYear(int year)           { this.year = year; }

    // ─── Getters ──────────────────────────────────────────────────────────
    public int    getStudentId() { return studentId; }
    public String getName()      { return name; }
    public String getCourse()    { return course; }
    public int    getYear()      { return year; }

    // ─── Display ──────────────────────────────────────────────────────────
    public void display() {
        System.out.println("  ┌─────────────────────────────────────┐");
        System.out.printf ("  │  Student ID  : %-20d │%n", studentId);
        System.out.printf ("  │  Name        : %-20s │%n", name);
        System.out.printf ("  │  Course      : %-20s │%n", course);
        System.out.printf ("  │  Year        : %-20d │%n", year);
        System.out.println("  └─────────────────────────────────────┘");
    }
}
