package com.fabiojuchem.peopleapi.domain.person.DTO;

import com.fabiojuchem.peopleapi.domain.contact.DTO.ContactDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = -6041767046974433503L;

    private UUID id;

    private String name;

    private String document;

    private LocalDate birthDate;

    private List<ContactDTO> contacts;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.document = person.getDocument();
        this.birthDate = person.getBirthDate();
        this.contacts = person.getContacts().stream().map(ContactDTO::new).collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }
}
