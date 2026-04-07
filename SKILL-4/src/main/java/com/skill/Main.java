package com.skill;

import com.skill.annotation.AppConfig;
import com.skill.xml.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SKILL-4: Spring DI — Constructor and Setter Injection
 *
 * Demonstrates:
 *   Part A → XML Configuration
 *     - Bean 1: Constructor Injection (all fields via <constructor-arg>)
 *     - Bean 2: Setter Injection (course and year overridden via <property>)
 *
 *   Part B → Annotation Configuration
 *     - @Component bean auto-discovered via @ComponentScan
 *     - Constructor Injection via @Value on constructor parameters
 *     - Setter Injection via @Value on setter methods
 */
public class Main {

    public static void main(String[] args) {

        banner("SKILL-4: Spring DI — Constructor & Setter Injection");

        // ══════════════════════════════════════════════════════════════
        //  PART A — XML Configuration
        // ══════════════════════════════════════════════════════════════
        section("PART A — XML Configuration (applicationContext.xml)");

        // Load Spring IoC container from XML config
        ApplicationContext xmlContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n  ── Bean 1: Constructor Injection ──────────────────");
        Student constructorStudent = xmlContext.getBean("studentConstructor", Student.class);
        constructorStudent.display();

        System.out.println("\n  ── Bean 2: Setter Injection ────────────────────────");
        System.out.println("  (course and year are overridden by <property> tags)\n");
        Student setterStudent = xmlContext.getBean("studentSetter", Student.class);
        setterStudent.display();

        ((ClassPathXmlApplicationContext) xmlContext).close();

        // ══════════════════════════════════════════════════════════════
        //  PART B — Annotation Configuration
        // ══════════════════════════════════════════════════════════════
        section("PART B — Annotation Configuration (@Component + @Value)");

        System.out.println();

        // Load Spring IoC container from Java annotation config
        AnnotationConfigApplicationContext annotationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println();
        System.out.println("  ── Bean: @Component Student (Constructor + Setter Injection) ──");
        com.skill.annotation.Student annotationStudent =
                annotationContext.getBean("studentBean", com.skill.annotation.Student.class);

        System.out.println();
        annotationStudent.display();

        annotationContext.close();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║  Both XML and Annotation DI demos completed!         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");
    }

    // ─── Formatting helpers ───────────────────────────────────────────────────

    private static void banner(String title) {
        String line = "═".repeat(title.length() + 4);
        System.out.println("\n╔" + line + "╗");
        System.out.println("║  " + title + "  ║");
        System.out.println("╚" + line + "╝\n");
    }

    private static void section(String title) {
        System.out.println("\n┌─ " + title + " " + "─".repeat(Math.max(0, 68 - title.length())) + "┐");
    }
}
