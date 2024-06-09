package io.aeskandar.personservice.config;

import io.aeskandar.personservice.model.PersonDao;
import io.aeskandar.personservice.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.List;

@Configuration
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {

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
            PersonDao monica = new PersonDao(
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    "Monica",
                    "Geller",
                    29
            );
            PersonDao ross = new PersonDao(
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    "Ross",
                    "Geller",
                    35
            );

            personRepository.saveAll(List.of(ross, me, monica, joey));
        };
    }

}
