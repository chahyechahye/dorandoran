package com.doran.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "author", nullable = true)
    private String author;

    @Column(nullable = true)
    private String publisher;
}
