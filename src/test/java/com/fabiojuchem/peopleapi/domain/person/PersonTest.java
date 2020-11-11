package com.fabiojuchem.peopleapi.domain.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void of_shouldBuildNewPerson() {
        String name = "Fake Test Name";
        String document = "154156165451";
        LocalDate birthDate = LocalDate.now();

        var person = Person.of(name, document, birthDate);

        assertEquals(name, person.getName());
        assertEquals(document, person.getDocument());
        assertEquals(birthDate, person.getBirthDate());
    }
}