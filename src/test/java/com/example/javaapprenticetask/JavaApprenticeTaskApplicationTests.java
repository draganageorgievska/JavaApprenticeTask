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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class JavaApprenticeTaskApplicationTests {
    @Autowired
    AuthorServiceImpl authorService;
    @Autowired
    BookServiceImpl bookService;
    public void createAuthor(){
        Date date=new Date(1989, Calendar.DECEMBER,11);
        AuthorDto authorDto=new AuthorDto("Maya","Angelou",date);
        this.authorService.createNewAuthor(authorDto);
    }
    public void createBook() throws ChangeSetPersister.NotFoundException {
        BookDto bookDto=new BookDto("I Know Why The Caged Bird Sings",1L, Genre.NONFICTION);
        this.bookService.createNewBook(bookDto).get();
    }
    @Test
    public void test_getAuthorValidId() {
        Date date=new Date(1989, Calendar.DECEMBER,11);
        AuthorDto authorDto=new AuthorDto("Maya","Angelou",date);
        this.authorService.createNewAuthor(authorDto);
        Optional<Author> author=this.authorService.getAuthor(1L);
        assertEquals(author.get().getFirstName(),authorDto.getFirstName());
    }
    @Test
    public void test_getAuthorInvalidId(){
        assertTrue(this.authorService.getAuthor(10L).isEmpty());
    }
    @Test
    public void test_authorCreate(){
        Date date=new Date(1989, Calendar.DECEMBER,11);
        AuthorDto authorDto=new AuthorDto("Maya","Angelou",date);
        Author author=this.authorService.createNewAuthor(authorDto).get();
        assertEquals(1L,author.getId());
        assertEquals(authorDto.getFirstName(),author.getFirstName());
        assertEquals(authorDto.getLastName(),author.getLastName());
        assertEquals(authorDto.getDateOfBirth(),author.getDateOfBirth());
    }
    @Test
    public void test_createBookUnsuccessful() {
        BookDto bookDto1=new BookDto("I Know Why The Caged Bird Sings",23L, Genre.NONFICTION);
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.createNewBook(bookDto1));
    }
    @Test
    public void test_createBookSuccessful() throws ChangeSetPersister.NotFoundException {
        createAuthor();
        BookDto bookDto1=new BookDto("I Know Why The Caged Bird Sings",1L, Genre.NONFICTION);
        Book book=this.bookService.createNewBook(bookDto1).get();
        assertEquals(bookDto1.getAuthor(),book.getAuthor().getId());
        assertEquals(bookDto1.getTitle(),book.getTitle());
        assertEquals(bookDto1.getGenre(),book.getGenre());
    }
    @Test
    public void test_getBooks() throws ChangeSetPersister.NotFoundException {
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
    public void test_deleteBook() throws ChangeSetPersister.NotFoundException {
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
    public void test_update() throws ChangeSetPersister.NotFoundException {
        createAuthor();
        createBook();
        System.out.println(this.bookService.getAllBooks().size());
        BookDto bookDto=new BookDto("I Know Why",1L,Genre.FICTION);
        Optional<Book> book=this.bookService.updateBook(2L,bookDto);
        assertEquals(bookDto.getTitle(),book.get().getTitle());
        assertEquals(bookDto.getAuthor(),book.get().getAuthor().getId());
        assertEquals(bookDto.getGenre(),book.get().getGenre());
    }
    @Test
    public void test_UpdateBookDoesNotExist(){
        createAuthor();
        BookDto bookDto=new BookDto("I Know Why",1L,Genre.FICTION);
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.updateBook(1L,bookDto));
    }
    @Test
    public void test_UpdateAuthorDoesNotExist(){
        createAuthor();
        BookDto bookDto=new BookDto("I Know Why",10L,Genre.FICTION);
        assertThrows(ChangeSetPersister.NotFoundException.class,()->this.bookService.updateBook(2L,bookDto));
    }
}
