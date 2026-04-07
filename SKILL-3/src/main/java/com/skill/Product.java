package com.skill;

import javax.persistence.*;

/**
 * Product Entity — same entity as SKILL-2, reused in SKILL-3 for HQL demonstrations.
 *
 * Fields: id (PK, auto-generated), name, description, price, quantity
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Product() {}

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
    public void setDescription(String d)     { this.description = d; }

    public double getPrice()                 { return price; }
    public void setPrice(double price)       { this.price = price; }

    public int getQuantity()                 { return quantity; }
    public void setQuantity(int quantity)    { this.quantity = quantity; }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "Product { id=%-2d  name=%-22s  price=%8.2f  qty=%-4d  desc='%s' }",
            id, name, price, quantity, description
        );
    }
}
