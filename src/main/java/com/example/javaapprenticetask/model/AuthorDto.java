package com.example.javaapprenticetask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class AuthorDto {
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dateOfBirth;
    public AuthorDto(String firstName,String lastName, Date dateOfBirth){
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
    }
}
