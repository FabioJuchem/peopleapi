package com.fabiojuchem.peopleapi.domain.contact;

import com.fabiojuchem.peopleapi.domain.person.Person;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // JPA Required
    public Contact() {
    }

    public static Contact of(String name, Long phoneNumber, String email, Person person) {
        Contact contact = new Contact();
        contact.name = name;
        contact.phoneNumber = phoneNumber;
        contact.email = email;
        contact.person = person;
        return contact;
    }

    public UUID getId() {
        return id;
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

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!id.equals(contact.id)) return false;
        if (!name.equals(contact.name)) return false;
        if (!phoneNumber.equals(contact.phoneNumber)) return false;
        return email.equals(contact.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
