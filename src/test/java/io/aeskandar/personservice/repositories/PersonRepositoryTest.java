package io.aeskandar.personservice.repositories;

import io.aeskandar.personservice.model.PersonDao;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        PersonDao lea = new PersonDao(
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "Lea",
                "Tribbiani",
                21
        );
        PersonDao me = new PersonDao(
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "Abdulkarim",
                "Eskandar",
                31
        );
        PersonDao joey = new PersonDao(
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "Joey",
                "Tribbiani",
                50
        );
        personRepository.saveAll(List.of(me, joey, lea));
    }

    @Test
    void shouldFindAllPersons() {
        List<PersonDao> expected = personRepository.findAll();

        Assertions.assertThat(expected).isNotNull().hasSize(2);
    }

    @Test
    void shouldFindPersonByLastNameAndFirstName() {
        Optional<PersonDao> expected = personRepository.findByFirstNameAndLastName("Joey", "Tribbiani");

        Assertions.assertThat(expected).isPresent();
        Assertions.assertThat(expected.get().getId()).isNotNegative();
        Assertions.assertThat(expected.get().getFirstName()).isEqualTo("Joey");

    }

    @Test
    void findAllByLastName() {
        List<PersonDao> expected = personRepository.findAllByLastName("Tribbiani");

        Assertions.assertThat(expected).hasSize(2);
    }
}