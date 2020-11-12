package com.fabiojuchem.peopleapi.domain.person.DTO;

import br.com.caelum.stella.bean.validation.CPF;
import com.fabiojuchem.peopleapi.domain.contact.DTO.ContactDTO;
import com.fabiojuchem.peopleapi.domain.contact.DTO.PersistContactDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fabiojuchem.peopleapi.infrastructure.dictionary.MessageDictionary.*;

public class PersistPersonDTO implements Serializable {

    private static final long serialVersionUID = -4020310723791274961L;

    @NotNull(message = PERSON_NAME_REQUIRED)
    private String name;

    @CPF(message = PERSON_DOCUMENT_INVALID)
    @NotNull(message = PERSON_DOCUMENT_REQUIRED)
    private String document;

    @NotNull(message = PERSON_BIRTH_DATE_REQUIRED)
    private LocalDate birthDate;

    @Valid
    @NotEmpty(message = PERSON_CONTACT_REQUIRED)
    private List<PersistContactDTO> contacts;

    public PersistPersonDTO() {
    }

    public PersistPersonDTO(Person person) {
        this.name = person.getName();
        this.document = person.getDocument();
        this.birthDate = person.getBirthDate();
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

    public List<PersistContactDTO> getContacts() {
        return contacts;
    }
}
