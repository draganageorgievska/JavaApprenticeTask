package com.example.javaapprenticetask;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.AuthorDto;
import com.example.javaapprenticetask.model.Book;
import com.example.javaapprenticetask.model.BookDto;
import com.example.javaapprenticetask.model.enumerations.Genre;
import com.example.javaapprenticetask.service.impl.AuthorServiceImpl;
import com.example.javaapprenticetask.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class JavaApprenticeTaskApplicationTests {
    @Autowired
    AuthorServiceImpl authorService;
    @Autowired
    BookServiceImpl bookService;
    public void createAuthor() throws ParseException {
        AuthorDto authorDto=new AuthorDto("Maya","Angelou","1989-11-11");
        this.authorService.createNewAuthor(authorDto);
    }
    public void createBook() throws ChangeSetPersister.NotFoundException {
        List<Long> authors=new ArrayList<>();
        authors.add(1L);
        BookDto bookDto=new BookDto("I Know Why The Caged Bird Sings",authors, Genre.NONFICTION);
        this.bookService.createNewBook(bookDto).get();
    }
    @Test
    public void test_getAuthorValidId() throws ParseException {
        AuthorDto authorDto=new AuthorDto("Maya","Angelou","1989-11-11");
        this.authorService.createNewAuthor(authorDto);
        Optional<Author> author=this.authorService.getAuthor(1L);
        assertEquals(author.get().getFirstName(),authorDto.getFirstName());
    }
    @Test
    public void test_getAuthorInvalidId(){
        assertTrue(this.authorService.getAuthor(10L).isEmpty());
    }
    @Test
    public void test_authorCreate() throws ParseException {
        AuthorDto authorDto=new AuthorDto("Maya","Angelou","1989-11-11");
        Author author=this.authorService.createNewAuthor(authorDto).get();
        assertEquals(1L,author.getId());
        assertEquals(authorDto.getFirstName(),author.getFirstName());
        assertEquals(authorDto.getLastName(),author.getLastName());
        assertEquals(authorDto.getDateOfBirth(),author.getDateOfBirth());
    }
    @Test
    public void test_createBookUnsuccessful() {
        List<Long> authors=new ArrayList<>();
        authors.add(23L);
        BookDto bookDto1=new BookDto("I Know Why The Caged Bird Sings",authors, Genre.NONFICTION);
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.createNewBook(bookDto1));
    }
    @Test
    public void test_createBookSuccessful() throws ChangeSetPersister.NotFoundException, ParseException {
        createAuthor();
        List<Long> authors=new ArrayList<>();
        authors.add(1L);
        BookDto bookDto1=new BookDto("I Know Why The Caged Bird Sings",authors, Genre.NONFICTION);
        Book book=this.bookService.createNewBook(bookDto1).get();
        assertEquals(bookDto1.getAuthors(),book.getAuthors().stream().map(Author::getId).collect(Collectors.toList()));
        assertEquals(bookDto1.getTitle(),book.getTitle());
        assertEquals(bookDto1.getGenre(),book.getGenre());
    }
    @Test
    public void test_getBooks() throws ChangeSetPersister.NotFoundException, ParseException {
        createAuthor();
        createBook();
        createBook();
        List<Book> books=this.bookService.getAllBooks();
        assertEquals(2,books.size());
    }
    @Test
    public void test_getBooksEmpty(){
        List<Book> books=this.bookService.getAllBooks();
        assertEquals(0,books.size());
    }
    @Test
    public void test_deleteBook() throws ChangeSetPersister.NotFoundException, ParseException {
        createAuthor();
        createBook();
        this.bookService.deleteBook(2L);
        assertFalse(this.bookService.getBook(2L).isPresent());
    }
    @Test
    public void test_deleteException(){
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.deleteBook(5L));
    }
    @Test
    public void test_update() throws ChangeSetPersister.NotFoundException, ParseException {
        createAuthor();
        createAuthor();
        createBook();
        List<Long> authors=new ArrayList<>();
        authors.add(1L);
        authors.add(2L);
        BookDto bookDto=new BookDto("I Know Why",authors,Genre.FICTION);
        Optional<Book> book=this.bookService.updateBook(3L,bookDto);
        assertEquals(bookDto.getTitle(),book.get().getTitle());
        assertEquals(bookDto.getAuthors(),book.get().getAuthors().stream().map(Author::getId).collect(Collectors.toList()));
        assertEquals(bookDto.getGenre(),book.get().getGenre());
    }
    @Test
    public void test_UpdateBookDoesNotExist() throws ParseException {
        createAuthor();
        List<Long> authors=new ArrayList<>();
        authors.add(1L);
        BookDto bookDto=new BookDto("I Know Why",authors,Genre.FICTION);
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.updateBook(1L,bookDto));
    }
    @Test
    public void test_UpdateAuthorDoesNotExist() throws ParseException {
        createAuthor();
        List<Long> authors=new ArrayList<>();
        authors.add(10L);
        BookDto bookDto=new BookDto("I Know Why",authors,Genre.FICTION);
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.updateBook(2L,bookDto));
    }
}
