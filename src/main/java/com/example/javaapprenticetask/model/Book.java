package com.example.javaapprenticetask.model;

import com.example.javaapprenticetask.model.enumerations.Genre;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToMany
    private List<Author> authors;
    private Genre genre;
    public Book(String title, List<Author> authors,Genre genre){
        this.title=title;
        this.authors=authors;
        this.genre=genre;
    }

    public Book() {

    }
}
