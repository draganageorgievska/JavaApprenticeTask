package com.example.javaapprenticetask.model;

import com.example.javaapprenticetask.model.enumerations.Genre;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private String title;
    private List<Long> authors;
    private Genre genre;
    public BookDto(String title, List<Long> authorIds,Genre genre){
        this.title=title;
        this.authors =authorIds;
        this.genre=genre;
    }
}
