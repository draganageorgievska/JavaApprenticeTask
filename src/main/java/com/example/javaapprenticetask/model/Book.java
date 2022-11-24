package com.example.javaapprenticetask.model;

import com.example.javaapprenticetask.model.enumerations.Genre;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    private Author author;
    private Genre genre;
    public Book(String title, Author author,Genre genre){
        this.title=title;
        this.author=author;
        this.genre=genre;
    }

    public Book() {

    }
}
