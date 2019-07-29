package com.mrojasabregu.mortality.prediction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "persons")
public class Person {

    @Id
    private String id;
    private String firstName;
    private String LastName;
    private Date birthDate;
    private int age;
    private Date deathDate;
}
