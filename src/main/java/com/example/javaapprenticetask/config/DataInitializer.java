package com.example.javaapprenticetask.config;

import com.example.javaapprenticetask.model.AuthorDto;
import com.example.javaapprenticetask.model.BookDto;
import com.example.javaapprenticetask.model.enumerations.Genre;
import com.example.javaapprenticetask.service.AuthorService;
import com.example.javaapprenticetask.service.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Component
public class DataInitializer {
    private final AuthorService authorService;
    private final BookService bookService;

    public DataInitializer(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }
    @PostConstruct
    public void init() throws ChangeSetPersister.NotFoundException {
        Calendar cal=Calendar.getInstance();
        this.authorService.createNewAuthor(new AuthorDto("Megan","Nolan",cal.getTime()));
        this.authorService.createNewAuthor(new AuthorDto("Ai","Weiwei",cal.getTime()));
        this.bookService.createNewBook(new BookDto("Acts of Desperation", 1L, Genre.FICTION));
        this.bookService.createNewBook(new BookDto("1000 Years of Joys and Sorrows",2L,Genre.NONFICTION));
    }
}
