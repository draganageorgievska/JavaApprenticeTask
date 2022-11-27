package com.example.javaapprenticetask.web.graphQLcontroller;

import com.example.javaapprenticetask.model.Book;
import com.example.javaapprenticetask.model.BookDto;
import com.example.javaapprenticetask.service.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
public class BookGraphQLController {
    private final BookService bookService;
    public BookGraphQLController(BookService bookService) {
        this.bookService = bookService;
    }
    @QueryMapping
    public Iterable<Book> getAllBooks(){
        return this.bookService.getAllBooks();
    }
    @QueryMapping
    public Optional<Book> getBook(@Argument Long id){
        return this.bookService.getBook(id);
    }
    @MutationMapping
    public Optional<Book> addBook(@Argument(name = "book") BookDto bookInput) throws ChangeSetPersister.NotFoundException {
        return this.bookService.createNewBook(bookInput);
    }
    @MutationMapping
    public Optional<Book> updateBook(@Argument Long id, @Argument(name = "book") BookDto bookInput) throws ChangeSetPersister.NotFoundException {
        return this.bookService.updateBook(id,bookInput);
    }
    @MutationMapping
    public void deleteBook(@Argument Long id) throws ChangeSetPersister.NotFoundException {
        this.bookService.deleteBook(id);
    }
}
