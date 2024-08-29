package com.graphqlapi.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="author")
    private String author;

    @Column(name="title")
    private String title;

    public Integer getId(){
        return id;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public void setTitle(String title){
        this.title=title;
    }
}
