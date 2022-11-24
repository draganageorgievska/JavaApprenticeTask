package com.example.javaapprenticetask.model;

import com.example.javaapprenticetask.model.enumerations.Genre;
import lombok.Data;

@Data
public class BookDto {
    private String title;
    private Long author;
    private Genre genre;
    public BookDto(String title, Long author,Genre genre){
        this.title=title;
        this.author=author;
        this.genre=genre;
    }
}
