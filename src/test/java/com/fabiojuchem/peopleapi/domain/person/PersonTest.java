package com.fabiojuchem.peopleapi.domain.person;

import com.fabiojuchem.peopleapi.domain.base.ContactTestDataBuilder;
import com.fabiojuchem.peopleapi.domain.base.PersonTestDataBuilder;
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

    @Test
    void addContact_newContact_shouldAddContact() {
        var person = PersonTestDataBuilder.newPerson();
        var contact = ContactTestDataBuilder.newContact(person);

        person.addContact(contact);

        assertEquals(1, person.getContacts().size());
    }
}