# Hibernate CRUD Operations - SKILL-2

## Overview
This project demonstrates **Hibernate CRUD operations** on a `Product` entity using **JPA annotations** and an **H2 in-memory database**.

Part of:  
**Course**: Full Stack Application Development (24SDCS02 / 24SDCS02E / 24SDCS02P / 24SDCS02L)  
**Department**: CSE, CS&IT, AI&DS  

---

## Project Structure
```
SKILL-2/
├── pom.xml                             # Maven config with Hibernate + H2 dependencies
└── src/
    └── main/
        ├── java/
        │   └── com/skill/
        │       ├── Product.java        # JPA Entity with CRUD fields
        │       └── Main.java           # Main class - performs all CRUD operations
        └── resources/
            └── hibernate.cfg.xml       # Hibernate + H2 DB configuration
```

---

## Prerequisites
- Java 11+
- Apache Maven 3.x

---

## How to Run
```bash
cd SKILL-2
mvn clean compile exec:java -Dexec.mainClass="com.skill.Main"
```

---

## CRUD Operations Demonstrated

| Operation | Description |
|-----------|-------------|
| **Create** | Inserts 3 Products: Laptop, Wireless Mouse, USB-C Hub |
| **Read**   | Retrieves a product by its `id` |
| **Update** | Modifies the `price` and `quantity` of a product |
| **Delete** | Removes a discontinued product by `id` |

---

## ID Generation Strategies
In `Product.java`, the `@GeneratedValue` strategy is set to `IDENTITY`.  
You can change it to observe how Hibernate handles primary key generation:

| Strategy | Description |
|----------|-------------|
| `IDENTITY` | DB auto-increments the ID (default) |
| `AUTO`     | Hibernate selects the best strategy per dialect |
| `SEQUENCE` | Uses a DB-level sequence object |

---

## Technologies
- **Hibernate ORM** `5.6.15.Final`
- **H2 In-Memory Database** `2.2.224`
- **JPA Annotations** (`javax.persistence.*`)
- **Maven** for build and dependency management
