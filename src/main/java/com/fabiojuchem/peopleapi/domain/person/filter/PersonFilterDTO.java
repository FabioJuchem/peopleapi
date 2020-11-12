package com.fabiojuchem.peopleapi.domain.person.filter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class PersonFilterDTO implements Serializable {

    private static final long serialVersionUID = -1497356692042668688L;

    private String name;

    private String document;

    private LocalDate birthDate;

    private String orderBy = "name";

    public PersonFilterDTO() {
        // JPA Required
    }


    public PersonFilterDTO(String name, String document, LocalDate birthDate) {
        this.name = name;
        this.document = document;
        this.birthDate = birthDate;
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

    public String getOrderBy() {
        return orderBy;
    }

    public boolean hasName() {
        return Objects.nonNull(name) && !name.isBlank();
    }

    public boolean hasDocument() {
        return Objects.nonNull(document) && !document.isBlank();
    }

    public boolean hasBirthDate() {
        return Objects.nonNull(birthDate) && !birthDate.toString().isBlank();
    }
}
