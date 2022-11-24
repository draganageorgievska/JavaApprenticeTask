package com.example.javaapprenticetask.web.controller;

import com.example.javaapprenticetask.model.Book;
import com.example.javaapprenticetask.model.BookDto;
import com.example.javaapprenticetask.service.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getBooks(){
        return this.bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return this.bookService.getBook(id).map(book->ResponseEntity.ok().body(book)).orElseGet(()-> ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) throws ChangeSetPersister.NotFoundException {
        return this.bookService.createNewBook(bookDto).map(book->ResponseEntity.ok().body(book)).orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody BookDto bookDto) throws ChangeSetPersister.NotFoundException {
        return this.bookService.updateBook(id,bookDto).map(book->ResponseEntity.ok().body(book)).orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        this.bookService.deleteBook(id);
        if(this.bookService.getBook(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
