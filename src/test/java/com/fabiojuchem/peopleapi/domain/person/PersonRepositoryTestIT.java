package com.fabiojuchem.peopleapi.domain.person;

import com.fabiojuchem.peopleapi.domain.base.PersonTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTestIT {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findPersonByName_whenExists_shouldReturnPerson() {
        var person = PersonTestDataBuilder.newPerson();
        personRepository.save(person);

        var result = personRepository.findPersonByName(person.getName());

        assertTrue(result.isPresent());
        assertEquals(person.getName(), result.get().getName());
        assertEquals(person.getDocument(), result.get().getDocument());
        assertEquals(person.getBirthDate(), result.get().getBirthDate());

        personRepository.delete(person);
    }

    @Test
    void findPersonByName_whenNotExists_shouldReturnNothing() {

        var result = personRepository.findPersonByName("FakeTest");

        assertTrue(result.isEmpty());

    }


}