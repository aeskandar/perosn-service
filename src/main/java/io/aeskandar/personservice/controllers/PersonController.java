package io.aeskandar.personservice.controllers;

import io.aeskandar.personservice.model.Person;
import io.aeskandar.personservice.model.PersonDto;
import io.aeskandar.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("person")
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PutMapping("person/create")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto dto) {
        return new ResponseEntity<>(personService.savePerson(dto), HttpStatus.CREATED);
    }
 }
