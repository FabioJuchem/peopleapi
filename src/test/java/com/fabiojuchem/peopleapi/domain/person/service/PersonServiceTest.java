package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.base.PersonTestDataBuilder;
import com.fabiojuchem.peopleapi.domain.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void getPersonByName_whenExists_shouldReturnPersonDTO() {
        var person = PersonTestDataBuilder.newPersonWithContacts();

        when(personRepository.findPersonByName(person.getName())).thenReturn(Optional.of(person));

        var result = personService.getPersonByName(person.getName());

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getDocument(), result.getDocument());
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getBirthDate(), result.getBirthDate());
        assertEquals(person.getContacts().get(0).getName(), result.getContacts().get(0).getName());
        assertEquals(person.getContacts().get(0).getEmail(), result.getContacts().get(0).getEmail());
        assertEquals(person.getContacts().get(0).getPhoneNumber(), result.getContacts().get(0).getPhoneNumber());
        assertEquals(person.getContacts().get(0).getId(), result.getContacts().get(0).getId());
    }

}