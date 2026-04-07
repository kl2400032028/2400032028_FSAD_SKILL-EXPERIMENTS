package com.skill;

import com.skill.config.AppConfig;
import com.skill.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 6. Load the Spring IoC container using ApplicationContext with Java configuration.
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Spring IoC Container Loaded Successfully!");

        // 7. Retrieve the Student bean and print all details, including injected Certification object.
        Student student = context.getBean(Student.class);
        System.out.println("\n--- Retrieved Student Details ---");
        System.out.println(student);
    }
}
