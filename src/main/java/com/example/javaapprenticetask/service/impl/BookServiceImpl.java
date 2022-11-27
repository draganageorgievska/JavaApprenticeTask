package com.example.javaapprenticetask.service.impl;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.Book;
import com.example.javaapprenticetask.model.BookDto;
import com.example.javaapprenticetask.repository.AuthorRepository;
import com.example.javaapprenticetask.repository.BookRepository;
import com.example.javaapprenticetask.service.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(Long id) {
        Book book=this.bookRepository.findBookById(id);
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> createNewBook(BookDto bookDto) throws ChangeSetPersister.NotFoundException {
        List<Author> authors=this.authorRepository.findAllById(bookDto.getAuthors());
        if(authors.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        Book book=new Book(bookDto.getTitle(),authors,bookDto.getGenre());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> updateBook(Long bookId,BookDto bookDto) throws ChangeSetPersister.NotFoundException {
        List<Author> authors=this.authorRepository.findAllById(bookDto.getAuthors());
        if(authors.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        Book book=this.bookRepository.findBookById(bookId);
        if(book==null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        else {
            book.setTitle(bookDto.getTitle());
            book.setAuthors(authors);
            book.setGenre(bookDto.getGenre());
            return Optional.of(this.bookRepository.save(book));
        }
    }

    @Override
    public void deleteBook(Long id) throws ChangeSetPersister.NotFoundException {
        Book book=this.bookRepository.findBookById(id);
        if(book==null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        else{
            this.bookRepository.delete(book);
        }
    }
}
