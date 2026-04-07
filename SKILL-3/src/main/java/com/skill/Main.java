package com.skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * SKILL-3: Hibernate HQL — Sorting, Pagination, Filtering & Aggregate Functions
 *
 * Extends the Product entity from SKILL-2 and demonstrates:
 *  1.  Insert 5-8 product records
 *  2.  Sort by price ASC
 *  3.  Sort by price DESC
 *  4.  Sort by quantity (highest first)
 *  5.  Pagination – Page 1 (first 3 products)
 *  6.  Pagination – Page 2 (next 3 products)
 *  7.  Aggregate – count all products
 *  8.  Aggregate – count where quantity > 0
 *  9.  Aggregate – count grouped by description
 *  10. Aggregate – min and max price
 *  11. GROUP BY description
 *  12. WHERE filter – price range (1000 to 20000)
 *  13. LIKE – names starting with 'L'
 *  14. LIKE – names ending with 'k'
 *  15. LIKE – names containing 'a' anywhere
 *  16. LIKE – names with exact 6-character length
 */
public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        banner("SKILL-3: Hibernate HQL Demo");

        // ── STEP 1: Insert 7 product records ─────────────────────────────────
        section("STEP 1 — Inserting 7 Product Records");
        insertProducts();

        // ── STEP 2 & 3: Sort by price ASC / DESC ────────────────────────────
        section("STEP 2 — Sort by Price ASC");
        listQuery("FROM Product p ORDER BY p.price ASC");

        section("STEP 3 — Sort by Price DESC");
        listQuery("FROM Product p ORDER BY p.price DESC");

        // ── STEP 4: Sort by quantity (highest first) ─────────────────────────
        section("STEP 4 — Sort by Quantity DESC (highest first)");
        listQuery("FROM Product p ORDER BY p.quantity DESC");

        // ── STEP 5 & 6: Pagination ───────────────────────────────────────────
        section("STEP 5 — Pagination: Page 1 (first 3 products, sorted by price)");
        paginate("FROM Product p ORDER BY p.price ASC", 0, 3);

        section("STEP 6 — Pagination: Page 2 (next 3 products, sorted by price)");
        paginate("FROM Product p ORDER BY p.price ASC", 3, 3);

        // ── STEP 7: Count all products ───────────────────────────────────────
        section("STEP 7 — Aggregate: Count ALL Products");
        scalarQuery("SELECT COUNT(p) FROM Product p");

        // ── STEP 8: Count where quantity > 0 ────────────────────────────────
        section("STEP 8 — Aggregate: Count Products WHERE quantity > 0");
        scalarQuery("SELECT COUNT(p) FROM Product p WHERE p.quantity > 0");

        // ── STEP 9: Count grouped by description ────────────────────────────
        section("STEP 9 — Aggregate: Count Products GROUP BY Description");
        groupQuery("SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description");

        // ── STEP 10: Min and Max price ───────────────────────────────────────
        section("STEP 10 — Aggregate: MIN and MAX Price");
        minMaxQuery();

        // ── STEP 11: GROUP BY description ───────────────────────────────────
        section("STEP 11 — GROUP BY: Products grouped by Description");
        groupQuery("SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description ORDER BY COUNT(p) DESC");

        // ── STEP 12: WHERE price range filter ───────────────────────────────
        section("STEP 12 — WHERE Filter: Price between ₹1,000 and ₹20,000");
        listQuery("FROM Product p WHERE p.price BETWEEN 1000 AND 20000 ORDER BY p.price ASC");

        // ── STEP 13: LIKE — names starting with 'L' ─────────────────────────
        section("STEP 13 — LIKE: Names starting with 'L'");
        listQuery("FROM Product p WHERE p.name LIKE 'L%'");

        // ── STEP 14: LIKE — names ending with 'b' ───────────────────────────
        section("STEP 14 — LIKE: Names ending with 'b'");
        listQuery("FROM Product p WHERE p.name LIKE '%b'");

        // ── STEP 15: LIKE — names containing 'a' anywhere ───────────────────
        section("STEP 15 — LIKE: Names containing 'a' anywhere");
        listQuery("FROM Product p WHERE p.name LIKE '%a%'");

        // ── STEP 16: LIKE — names with exact 6-character length ─────────────
        section("STEP 16 — LIKE: Names with exactly 6 characters");
        listQuery("FROM Product p WHERE p.name LIKE '______'"); // 6 underscores

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  All HQL queries executed successfully!  ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        sessionFactory.close();
    }

    // ─── Helper: Insert all product records ──────────────────────────────────

    private static void insertProducts() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(new Product("Laptop",        "Electronics",    75000.00, 50));
            session.save(new Product("Wireless Mouse","Electronics",     1500.00, 200));
            session.save(new Product("USB-C Hub",     "Electronics",     3500.00, 120));
            session.save(new Product("Keyboard",      "Electronics",     2500.00, 180));
            session.save(new Product("Monitor",       "Electronics",    18000.00,  60));
            session.save(new Product("Webcam",        "Electronics",     4800.00,  90));
            session.save(new Product("Headset",       "Electronics",     6000.00, 150));

            session.getTransaction().commit();
        }
        System.out.println("  → 7 products inserted successfully.\n");
    }

    // ─── Helper: Generic HQL list query ──────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static void listQuery(String hql) {
        System.out.println("  HQL: " + hql);
        System.out.println("  " + "─".repeat(90));
        try (Session session = sessionFactory.openSession()) {
            List<Product> results = session.createQuery(hql).list();
            if (results.isEmpty()) {
                System.out.println("  (no results)");
            } else {
                results.forEach(p -> System.out.println("  " + p));
            }
        }
        System.out.println();
    }

    // ─── Helper: Pagination ───────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static void paginate(String hql, int firstResult, int maxResults) {
        System.out.printf("  HQL: %s  [firstResult=%d, maxResults=%d]%n", hql, firstResult, maxResults);
        System.out.println("  " + "─".repeat(90));
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResults);
            List<Product> results = query.list();
            results.forEach(p -> System.out.println("  " + p));
        }
        System.out.println();
    }

    // ─── Helper: Scalar aggregate (single value) ──────────────────────────────

    private static void scalarQuery(String hql) {
        System.out.println("  HQL: " + hql);
        System.out.println("  " + "─".repeat(90));
        try (Session session = sessionFactory.openSession()) {
            Object result = session.createQuery(hql).uniqueResult();
            System.out.println("  → Result: " + result);
        }
        System.out.println();
    }

    // ─── Helper: GROUP BY query ───────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static void groupQuery(String hql) {
        System.out.println("  HQL: " + hql);
        System.out.println("  " + "─".repeat(90));
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> rows = session.createQuery(hql).list();
            rows.forEach(row -> System.out.printf("  → %-20s : count = %s%n", row[0], row[1]));
        }
        System.out.println();
    }

    // ─── Helper: Min/Max price query ─────────────────────────────────────────

    private static void minMaxQuery() {
        String hql = "SELECT MIN(p.price), MAX(p.price) FROM Product p";
        System.out.println("  HQL: " + hql);
        System.out.println("  " + "─".repeat(90));
        try (Session session = sessionFactory.openSession()) {
            Object[] result = (Object[]) session.createQuery(hql).uniqueResult();
            System.out.printf("  → Minimum Price : ₹%.2f%n", result[0]);
            System.out.printf("  → Maximum Price : ₹%.2f%n", result[1]);
        }
        System.out.println();
    }

    // ─── Formatting helpers ───────────────────────────────────────────────────

    private static void banner(String title) {
        String line = "═".repeat(title.length() + 4);
        System.out.println("\n╔" + line + "╗");
        System.out.println("║  " + title + "  ║");
        System.out.println("╚" + line + "╝\n");
    }

    private static void section(String title) {
        System.out.println("┌─ " + title + " " + "─".repeat(Math.max(0, 72 - title.length())) + "┐");
    }
}
