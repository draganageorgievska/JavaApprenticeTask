package com.example.javaapprenticetask.model;

import lombok.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class AuthorDto {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    public AuthorDto(String firstName,String lastName, String dateOfBirth) throws ParseException {
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
    }
}
