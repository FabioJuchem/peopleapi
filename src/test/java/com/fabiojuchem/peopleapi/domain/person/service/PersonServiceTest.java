package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.base.PersonTestDataBuilder;
import com.fabiojuchem.peopleapi.domain.contact.DTO.PersistContactDTO;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersistPersonDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;
import com.fabiojuchem.peopleapi.domain.person.PersonRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersistPersonDTO persistPersonDTO;

    @Mock
    private PersistContactDTO persistContactDTO;

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

    @Test
    void save_newRequest_shouldSaveNewPerson() {
        var person = PersonTestDataBuilder.newPerson();

        when(persistPersonDTO.getName()).thenReturn(person.getName());
        when(persistPersonDTO.getDocument()).thenReturn(person.getDocument());
        when(persistPersonDTO.getBirthDate()).thenReturn(person.getBirthDate());
        when(personRepository.save(any(Person.class))).thenReturn(person);

        var result = personService.save(persistPersonDTO);

        assertNotNull(result);
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getDocument(), result.getDocument());
        assertEquals(person.getBirthDate(), result.getBirthDate());
    }

    @Test
    void addContact_validRequest_shouldAddContact() throws NotFoundException {
        var person = PersonTestDataBuilder.newPerson();
        var name = "Fake";
        var phoneNumber = 151515115L;
        var email = "email@email.com";

        when(personRepository.getOne(person.getId())).thenReturn(person);
        when(persistContactDTO.getName()).thenReturn(name);
        when(persistContactDTO.getPhoneNumber()).thenReturn(phoneNumber);
        when(persistContactDTO.getEmail()).thenReturn(email);

        var result = personService.addContact(persistContactDTO, person.getId());

        assertNotNull(result);
        verify(personRepository).save(person);
        assertEquals(name, result.getName());
        assertEquals(phoneNumber, result.getPhoneNumber());
        assertEquals(email, result.getEmail());
    }

    @Test
    void addContact_personNotExists_shouldAddContact() throws NotFoundException {
        var person = PersonTestDataBuilder.newPerson();

        when(personRepository.getOne(person.getId())).thenReturn(null);

        assertThrows(NotFoundException.class, () ->personService.addContact(persistContactDTO, person.getId()));

        verify(personRepository, never()).save(person);
    }

    @Test
    void deletePerson_whenPersonExists_shouldDeletePerson() throws NotFoundException {
        var person = PersonTestDataBuilder.newPerson();

        when(personRepository.getOne(person.getId())).thenReturn(person);

        personService.deletePerson(person.getId());

        verify(personRepository).delete(person);
    }

    @Test
    void deletePerson_whenPersonNotExists_shouldNotDeletePerson() throws NotFoundException {
        var person = PersonTestDataBuilder.newPerson();

        when(personRepository.getOne(person.getId())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> personService.deletePerson(person.getId()));

        verify(personRepository, never()).delete(person);
    }

}