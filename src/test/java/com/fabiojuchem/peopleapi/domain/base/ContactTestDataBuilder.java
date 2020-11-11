package com.fabiojuchem.peopleapi.domain.base;

import com.fabiojuchem.peopleapi.domain.contact.Contact;
import com.fabiojuchem.peopleapi.domain.person.Person;

public class ContactTestDataBuilder {

    public static final String NAME = "Fake Name";

    public static final Long PHONE_NUMBER = 4154544511L;

    public static final String EMAIL = "email@email.com";


    public static Contact newContact(Person person) {
        return Contact.of(NAME, PHONE_NUMBER, EMAIL, person);
    }
}
