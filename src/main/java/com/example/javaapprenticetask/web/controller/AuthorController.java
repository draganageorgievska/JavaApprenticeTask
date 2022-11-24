package com.example.javaapprenticetask.web.controller;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.AuthorDto;
import com.example.javaapprenticetask.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthor(@PathVariable Long id){
        return this.authorService.getAuthor(id).map(author -> ResponseEntity.ok().body(author)).orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDto authorDto){
        return this.authorService.createNewAuthor(authorDto).map(author->ResponseEntity.ok().body(author)).orElseGet(()->ResponseEntity.badRequest().build());
    }
}
