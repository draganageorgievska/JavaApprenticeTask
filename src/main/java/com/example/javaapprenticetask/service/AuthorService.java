package com.example.javaapprenticetask.service;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.AuthorDto;

import java.util.Date;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> createNewAuthor(AuthorDto author);
    Optional<Author> getAuthor(Long id);
}
