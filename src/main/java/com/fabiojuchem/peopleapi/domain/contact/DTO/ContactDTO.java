package com.fabiojuchem.peopleapi.domain.contact.DTO;

import com.fabiojuchem.peopleapi.domain.contact.Contact;

import java.io.Serializable;
import java.util.UUID;

public class ContactDTO implements Serializable {

    private static final long serialVersionUID = -2275881361201183783L;

    private UUID id;

    private String name;

    private String phoneNumber;

    private String email;

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.phoneNumber = contact.getPhoneNumber();
        this.email = contact.getEmail();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
