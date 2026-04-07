package com.skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SKILL-2: Hibernate CRUD Operations Demo
 *
 * Demonstrates complete CRUD operations on a Product entity:
 *   C - Create  : Insert multiple product records
 *   R - Read    : Retrieve a product by its ID
 *   U - Update  : Modify price and quantity of a product
 *   D - Delete  : Remove a discontinued product by its ID
 */
public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        // ── Build SessionFactory from hibernate.cfg.xml ──────────────────────
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        System.out.println("\n========================================");
        System.out.println("  SKILL-2: Hibernate CRUD Operations");
        System.out.println("========================================\n");

        // ── CREATE: Insert multiple Product records ───────────────────────────
        System.out.println("──────────────────────────────────────");
        System.out.println("  [CREATE] Inserting Products...");
        System.out.println("──────────────────────────────────────");

        Long id1 = createProduct(new Product("Laptop",       "High-performance laptop",            75000.00, 50));
        Long id2 = createProduct(new Product("Wireless Mouse","Ergonomic wireless mouse",            1500.00, 200));
        Long id3 = createProduct(new Product("USB-C Hub",    "7-in-1 USB-C hub with HDMI & SD",    3500.00, 120));

        System.out.printf("  → Inserted Product ID: %d%n", id1);
        System.out.printf("  → Inserted Product ID: %d%n", id2);
        System.out.printf("  → Inserted Product ID: %d%n%n", id3);

        // ── READ: Retrieve a product by ID ────────────────────────────────────
        System.out.println("──────────────────────────────────────");
        System.out.println("  [READ] Retrieving Product by ID...");
        System.out.println("──────────────────────────────────────");

        Product found = readProduct(id1);
        if (found != null) {
            System.out.println("  → Found: " + found);
        } else {
            System.out.printf("  → Product with ID %d not found.%n", id1);
        }
        System.out.println();

        // ── UPDATE: Modify price and quantity ─────────────────────────────────
        System.out.println("──────────────────────────────────────");
        System.out.println("  [UPDATE] Updating Product price & quantity...");
        System.out.println("──────────────────────────────────────");

        updateProduct(id1, 72000.00, 45);
        Product updated = readProduct(id1);
        System.out.println("  → After Update: " + updated);
        System.out.println();

        // ── DELETE: Remove a discontinued product ─────────────────────────────
        System.out.println("──────────────────────────────────────");
        System.out.println("  [DELETE] Deleting discontinued Product...");
        System.out.println("──────────────────────────────────────");

        deleteProduct(id3);
        Product deleted = readProduct(id3);
        if (deleted == null) {
            System.out.printf("  → Product ID %d successfully deleted.%n", id3);
        }
        System.out.println();

        System.out.println("========================================");
        System.out.println("  All CRUD operations completed.");
        System.out.println("========================================\n");

        sessionFactory.close();
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    private static Long createProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long generatedId = (Long) session.save(product);
            session.getTransaction().commit();
            return generatedId;
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────
    private static Product readProduct(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    private static void updateProduct(Long id, double newPrice, int newQuantity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                System.out.printf("  → Before Update: %s%n", product);
                product.setPrice(newPrice);
                product.setQuantity(newQuantity);
                session.update(product);
            }
            session.getTransaction().commit();
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    private static void deleteProduct(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                System.out.printf("  → Deleting: %s%n", product);
                session.delete(product);
            }
            session.getTransaction().commit();
        }
    }
}
