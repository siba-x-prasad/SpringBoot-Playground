package com.swsisolutions.springboot.model; 

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    private LocalDate published;

    // constructors
    public Book() {}
    public Book(String title, String author, LocalDate published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDate getPublished() { return published; }
    public void setPublished(LocalDate published) { this.published = published; }
}

