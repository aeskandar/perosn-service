package io.aeskandar.personservice.service;

import io.aeskandar.personservice.model.Person;
import io.aeskandar.personservice.model.PersonDao;
import io.aeskandar.personservice.model.PersonDto;
import io.aeskandar.personservice.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;


    public List<Person> getAllPersons() {
        return personRepository.findAll().stream().map(this::mapDaoToPerson).toList();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).map(this::mapDaoToPerson).orElseThrow(() -> new IllegalArgumentException(String.format("persons by id: %s is not found", id)));
    }

    public List<Person> getPersonByLastName(String lastName) {
        return personRepository.findAllByLastName(lastName).stream().map(this::mapDaoToPerson).toList();
    }

    public Person savePerson(PersonDto dto) {

        if (dto.getAge() <= 0) {
            throw new IllegalArgumentException("provided age is negative");
        }

        if (dto.getFirstName().isBlank() || dto.getLastName().isBlank()) {
            throw new IllegalArgumentException("provided names are empty");
        }

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        Optional<PersonDao> existingPerson = personRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());

        if (existingPerson.isPresent()) {
            throw new IllegalStateException("person already exists");
        }

        PersonDao dao = new PersonDao(null, currentTime, currentTime, dto.getFirstName(), dto.getLastName(), dto.getAge());

        return mapDaoToPerson(personRepository.save(dao));

    }

    private Person mapDaoToPerson(PersonDao personDao) {
        return new Person(personDao.getCreatedAt(), personDao.getUpdatedAt(), personDao.getFirstName(), personDao.getLastName(), personDao.getAge());
    }

}
