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

    private String body = "{\"name\":\"Fake Name\",\"document\": \" \" ,\"birthDate\": \" \"}";

    private String bodyWithContact =  "{\n" +
            "    \"name\": \"Fake Name\",\n" +
            "    \"document\": \"60745888054\",\n" +
            "    \"birthDate\": \"2020-10-10\",\n" +
            "    \"contacts\": [\n" +
            "            { \n" +
            "                \"name\": \"contactName\", \n" +
            "                \"phoneNumber\": \"15156151\",\n" +
            "                \"email\": \"email@email.com\" \n" +
            "            }\n" +
            "        ]\n" +
            "}";

    private String bodyWithContactEmailInvalid =  "{\n" +
            "    \"name\": \"Fake Name\",\n" +
            "    \"document\": \"60745888054\",\n" +
            "    \"birthDate\": \"2020-10-10\",\n" +
            "    \"contacts\": [\n" +
            "            { \n" +
            "                \"name\": \"contactName\", \n" +
            "                \"phoneNumber\": \"15156151\",\n" +
            "                \"email\": \"email\" \n" +
            "            }\n" +
            "        ]\n" +
            "}";

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

    @Test
    void getAllPersons_whenExists_shouldReturnPersonPageable() {

        given().port(port)
                .when()
                .body(body)
                .headers("Content-Type", "application/json")
                .get("api/v1/person/list?page=0&size=1")
                .then()
                .statusCode(200)
                .body("content.size()", equalTo(1))
                .body("pageable.pageSize", equalTo(1))
                .body("totalPages", equalTo(1));
    }

    @Test
    void save_whenCpfIsNotValid_shouldReturnException() {

        given().port(port)
                .when()
                .body(body)
                .headers("Content-Type", "application/json")
                .post("api/v1/person")
                .then()
                .statusCode(400);
    }

    @Test
    void save_everythingIsOk_shouldSaveNewPersonWithContact() {

        given().port(port)
                .when()
                .body(bodyWithContact)
                .headers("Content-Type", "application/json")
                .post("api/v1/person")
                .then()
                .statusCode(200)
                .body("name", equalTo("Fake Name"))
                .body("document", equalTo("60745888054"))
                .body("birthDate", equalTo("2020-10-10"))
                .body("contacts.size()", equalTo(1))
                .body("contacts[0].name", equalTo("contactName"))
                .body("contacts[0].phoneNumber", equalTo(15156151))
                .body("contacts[0].email", equalTo("email@email.com"));
    }

    @Test
    void save_emailInvalid_shouldNotSave() {

        given().port(port)
                .when()
                .body(bodyWithContactEmailInvalid)
                .headers("Content-Type", "application/json")
                .post("api/v1/person")
                .then()
                .statusCode(400);
    }
}