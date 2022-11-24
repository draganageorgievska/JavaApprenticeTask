package com.example.javaapprenticetask.service.impl;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.AuthorDto;
import com.example.javaapprenticetask.repository.AuthorRepository;
import com.example.javaapprenticetask.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> createNewAuthor(AuthorDto authorDto) {
        Author author=new Author(authorDto.getFirstName(),authorDto.getLastName(),authorDto.getDateOfBirth());
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> getAuthor(Long id) {
        Optional<Author> author=this.authorRepository.findById(id);
        return author;
    }
}
