package com.example.javaapprenticetask.web.graphQLcontroller;

import com.example.javaapprenticetask.model.Author;
import com.example.javaapprenticetask.model.AuthorDto;
import com.example.javaapprenticetask.service.AuthorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Optional;

@Controller
public class AuthorGraphQLController {
    private final AuthorService authorService;
    public AuthorGraphQLController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @QueryMapping
    public Optional<Author> getAuthor(@Argument Long id){
        return this.authorService.getAuthor(id);
    }
    @MutationMapping
    public Optional<Author> addAuthor(@Argument(name = "author") AuthorDto authorInput) {
        return this.authorService.createNewAuthor(authorInput);
    }
}
