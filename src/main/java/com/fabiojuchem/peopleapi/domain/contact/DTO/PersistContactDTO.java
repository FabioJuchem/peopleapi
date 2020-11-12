package com.fabiojuchem.peopleapi.domain.contact.DTO;

import com.fabiojuchem.peopleapi.domain.contact.Contact;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

import static com.fabiojuchem.peopleapi.infrastructure.dictionary.MessageDictionary.*;

public class PersistContactDTO implements Serializable {

    private static final long serialVersionUID = -2275881361201183783L;

    @NotNull(message = CONTACT_NAME_REQUIRED)
    private String name;

    @NotNull(message = CONTACT_PHONE_NUMBER_REQUIRED)
    private Long phoneNumber;

    @NotNull(message = CONTACT_EMAIL_REQUIRED)
    @Email(message = CONTACT_EMAIL_INVALID)
    private String email;

    public PersistContactDTO() {
    }

    public String getName() {
        return name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

}
