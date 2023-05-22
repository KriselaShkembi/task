package com.example.hinto.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private LocalDateTime date1;

    private Integer qty;

    // Constructor
    public Lesson() {
    }

    public Lesson(Long id, String title, LocalDateTime date1, Integer qty) {
        this.id = id;
        this.title = title;
        this.date1 = date1;
        this.qty = qty;
    }

    // Getters & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate1() {
        return date1;
    }

    public void setDate1(LocalDateTime date1) {
        this.date1 = date1;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
