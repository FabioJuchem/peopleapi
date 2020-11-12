package com.fabiojuchem.peopleapi.domain.base;

import com.fabiojuchem.peopleapi.domain.person.Person;

import java.time.LocalDate;

public class PersonTestDataBuilder {

    public static final String NAME = "Fake Name";

    public static final String DOCUMENT = "84848154111";

    public static final LocalDate BIRTH_DATE = LocalDate.now();


    public static Person newPerson() {
        return Person.of(NAME, DOCUMENT, BIRTH_DATE);
    }

    public static Person newPersonWithContacts() {
        Person person = newPerson();
        person.addContact(ContactTestDataBuilder.newContact(person));
        return person;
    }
}
