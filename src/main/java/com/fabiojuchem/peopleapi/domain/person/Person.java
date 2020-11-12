package com.fabiojuchem.peopleapi.domain.person;

import br.com.caelum.stella.bean.validation.CPF;
import com.fabiojuchem.peopleapi.domain.contact.Contact;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fabiojuchem.peopleapi.infrastructure.dictionary.MessageDictionary.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "document")
    private String document;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

    // JPA Required
    public Person() {
    }

    public static Person of(String name, String document, LocalDate birthDate) {
        var person = new Person();
        person.name = name;
        person.document = document;
        person.birthDate = birthDate;
        return person;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
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

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        if (!name.equals(person.name)) return false;
        if (!document.equals(person.document)) return false;
        if (!birthDate.equals(person.birthDate)) return false;
        return contacts.equals(person.contacts);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + document.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + contacts.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", birthDate=" + birthDate +
                ", contacts=" + contacts +
                '}';
    }
}
