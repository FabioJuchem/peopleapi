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

    private String order = "asc";

    public PersonFilterDTO() {
        // JPA Required
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
        return Objects.nonNull(name);
    }

    public boolean hasDocument() {
        return Objects.nonNull(document);
    }

    public boolean hasBirthDate() {
        return Objects.nonNull(birthDate);
    }
}
