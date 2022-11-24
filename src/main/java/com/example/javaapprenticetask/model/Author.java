package com.example.javaapprenticetask.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    public Author(String firstName,String lastName, Date dateOfBirth){
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
    }

    public Author() {

    }
}
