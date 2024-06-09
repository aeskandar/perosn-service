package io.aeskandar.personservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity(name = "Person")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PersonDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @SequenceGenerator( name = "person_sequence",
    sequenceName = "person_sequence",
    allocationSize = 1)
    Long id;

    Timestamp createdAt;

    Timestamp updatedAt;

    String firstName;

    String lastName;

    int age;

    public PersonDao(Timestamp createdAt, Timestamp updatedAt, String firstName, String lastName, int age) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
