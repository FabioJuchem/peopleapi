package com.fabiojuchem.peopleapi.presentation.resource;

import com.fabiojuchem.peopleapi.domain.base.PersonTestDataBuilder;
import com.fabiojuchem.peopleapi.domain.person.Person;
import com.fabiojuchem.peopleapi.domain.person.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonResourceTestIT {

    @Autowired
    private PersonRepository personRepository;

    @LocalServerPort
    private int port;

    private Person person;

    @BeforeEach
    void setUp() {
        person = PersonTestDataBuilder.newPersonWithContacts();
        personRepository.save(person);
    }

    @AfterEach
    void delete() {
        personRepository.delete(person);
    }

    @Test
    void getPersonByName_whenExists_shouldReturnPerson() {
        given().port(port)
                .when().get("api/v1/person?name=" + person.getName())
                .then()
                .statusCode(200)
                .body("id", equalTo(person.getId().toString()))
                .body("name", equalTo(person.getName()))
                .body("document", equalTo(person.getDocument()))
                .body("contacts.size()", equalTo(1))
                .body("contacts[0].name", equalTo(person.getContacts().get(0).getName()))
                .body("contacts[0].email", equalTo(person.getContacts().get(0).getEmail()))
                .body("contacts[0].phoneNumber", equalTo(person.getContacts().get(0).getPhoneNumber()))
                .body("contacts[0].id", equalTo(person.getContacts().get(0).getId().toString()));
    }
}