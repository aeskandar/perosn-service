package io.aeskandar.personservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Person {

    Timestamp createdAt;

    Timestamp updatedAt;

    String firstName;

    String lastName;

    int age;
}
