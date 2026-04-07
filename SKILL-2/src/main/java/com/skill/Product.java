package com.skill;

import javax.persistence.*;

/**
 * Product Entity - represents a product in the retail inventory system.
 *
 * ID Generation Strategy Notes:
 *  - GenerationType.IDENTITY  → DB auto-increments (works great with H2/MySQL)
 *  - GenerationType.AUTO      → Hibernate picks best strategy per dialect
 *  - GenerationType.SEQUENCE  → Uses a DB sequence object (change to this to observe sequence-based PKs)
 *
 * Change the strategy below to experiment with each approach.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // To test SEQUENCE: @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    // @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", allocationSize = 1)
    // To test AUTO:     @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // ─── Constructors ──────────────────────────────────────────────────────────

    public Product() { }

    public Product(String name, String description, double price, int quantity) {
        this.name        = name;
        this.description = description;
        this.price       = price;
        this.quantity    = quantity;
    }

    // ─── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId()                      { return id; }
    public void setId(Long id)               { this.id = id; }

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public String getDescription()           { return description; }
    public void setDescription(String desc)  { this.description = desc; }

    public double getPrice()                 { return price; }
    public void setPrice(double price)       { this.price = price; }

    public int getQuantity()                 { return quantity; }
    public void setQuantity(int quantity)    { this.quantity = quantity; }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "Product { id=%d, name='%s', description='%s', price=%.2f, quantity=%d }",
            id, name, description, price, quantity
        );
    }
}
