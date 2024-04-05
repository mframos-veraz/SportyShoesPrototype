package com.vodafone.deal.SportyShoesPrototype.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int userId;
    @Column(name = "purchase date")
    private LocalDateTime date;

    public Order() {
    }

    public Order(int id, int productId, int userId, LocalDateTime date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.date = date;
    }

    public Order(int productId, int userId, LocalDateTime date) {
        this.productId = productId;
        this.userId = userId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
