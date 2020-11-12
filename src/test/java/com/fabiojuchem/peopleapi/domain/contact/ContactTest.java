package com.fabiojuchem.peopleapi.domain.contact;

import com.fabiojuchem.peopleapi.domain.person.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    @Test
    void of_shouldBuildNewContact() {
        String name = "Fake Test Name";
        Long phoneNumber = 448825251414L;
        String email = "fake email";
        Person person = new Person();

        Contact contact = Contact.of(name, phoneNumber, email, person);

        assertEquals(name, contact.getName());
        assertEquals(phoneNumber, contact.getPhoneNumber());
        assertEquals(email, contact.getEmail());
        assertEquals(person, contact.getPerson());
    }

}