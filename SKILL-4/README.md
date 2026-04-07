# SKILL-4: Spring DI — Constructor & Setter Injection

## Overview
Demonstrates **Spring IoC** with **Constructor Injection** and **Setter Injection** through two distinct approaches:
- **Part A**: XML Configuration (`applicationContext.xml`)
- **Part B**: Annotation Configuration (`@Component`, `@Value`, `@ComponentScan`)

**Course**: Full Stack Application Development — 24SDCS02

---

## Project Structure
```
SKILL-4/
├── pom.xml
└── src/main/
    ├── java/com/skill/
    │   ├── Main.java                      # Loads both contexts and demos
    │   ├── xml/
    │   │   └── Student.java               # POJO for XML-based DI
    │   └── annotation/
    │       ├── Student.java               # @Component bean with @Value
    │       └── AppConfig.java             # @Configuration + @ComponentScan
    └── resources/
        └── applicationContext.xml         # XML bean definitions
```

---

## How to Run
```bash
cd SKILL-4
mvn clean compile exec:java -Dexec.mainClass="com.skill.Main"
```

---

## DI Approaches

### Part A — XML Configuration
| Bean ID | Injection Type | Details |
|---------|---------------|---------|
| `studentConstructor` | Constructor | All 4 fields via `<constructor-arg>` |
| `studentSetter` | Setter | `course` and `year` overridden via `<property>` |

### Part B — Annotation Configuration
| Annotation | Purpose |
|-----------|---------|
| `@Component` | Registers Student as a Spring bean |
| `@Value` on constructor | Constructor injection of student details |
| `@Value` on setters | Setter injection to override `course` and `year` |
| `@Configuration` | Marks `AppConfig` as a config class |
| `@ComponentScan` | Tells Spring where to scan for beans |

---

## Technologies
- **Spring Framework** 5.3.30 (`spring-context`)
- **Maven** 3.x
- **Java** 11
