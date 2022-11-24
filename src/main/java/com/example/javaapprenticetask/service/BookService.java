package com.example.javaapprenticetask.service;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.Book;
import com.example.javaapprenticetask.model.BookDto;
import com.example.javaapprenticetask.model.enumerations.Genre;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBook(Long id);
    Optional<Book> createNewBook(BookDto bookDto) throws ChangeSetPersister.NotFoundException;
    Optional<Book> updateBook(Long id,BookDto bookDto) throws ChangeSetPersister.NotFoundException;
    void deleteBook(Long id);
}
