package com.example.javaapprenticetask.repository;

import com.example.javaapprenticetask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    Book findBookByTitle(String title);
    Book findBookById(Long id);
}
