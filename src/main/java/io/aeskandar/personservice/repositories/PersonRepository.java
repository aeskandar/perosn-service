package io.aeskandar.personservice.repositories;

import io.aeskandar.personservice.model.PersonDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonDao, Long> {

    PersonDao getPersonByFirstName(String firstName);

    List<PersonDao> findAllByLastName(String lastName);

    Optional<PersonDao> findByFirstNameAndLastName(String firstName, String lastName);

}
