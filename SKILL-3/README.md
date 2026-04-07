# SKILL-3: Hibernate HQL Demo

## Overview
Extends SKILL-2 with advanced **HQL (Hibernate Query Language)** operations on the `Product` entity.
Demonstrates: **sorting**, **pagination**, **filtering**, **aggregate functions**, **GROUP BY**, **WHERE**, and **LIKE** queries.

**Course**: Full Stack Application Development — 24SDCS02 / 24SDCS02E / 24SDCS02P / 24SDCS02L

---

## Project Structure
```
SKILL-3/
├── pom.xml
└── src/main/
    ├── java/com/skill/
    │   ├── Product.java    # JPA Entity (same as SKILL-2)
    │   └── Main.java       # All HQL query demonstrations
    └── resources/
        └── hibernate.cfg.xml
```

---

## How to Run
```bash
cd SKILL-3
mvn clean compile exec:java -Dexec.mainClass="com.skill.Main"
```

---

## HQL Queries Demonstrated

| Step | Query Type | Description |
|------|-----------|-------------|
| 1 | INSERT | 7 product records |
| 2 | ORDER BY ASC | Sort by price ascending |
| 3 | ORDER BY DESC | Sort by price descending |
| 4 | ORDER BY DESC | Sort by quantity (highest first) |
| 5 | Pagination | Page 1 — first 3 products |
| 6 | Pagination | Page 2 — next 3 products |
| 7 | COUNT | Total number of products |
| 8 | COUNT + WHERE | Count products with quantity > 0 |
| 9 | COUNT + GROUP BY | Count grouped by description |
| 10 | MIN / MAX | Minimum and maximum price |
| 11 | GROUP BY | Group products by description |
| 12 | WHERE BETWEEN | Filter: price range ₹1,000–₹20,000 |
| 13 | LIKE | Names starting with 'L' |
| 14 | LIKE | Names ending with 'b' |
| 15 | LIKE | Names containing 'a' anywhere |
| 16 | LIKE | Names with exactly 6 characters |

---

## Technologies
- **Hibernate ORM** 5.6.15.Final
- **H2 In-Memory Database** 2.2.224
- **JPA Annotations** (`javax.persistence.*`)
- **Maven**
