package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.contact.Contact;
import com.fabiojuchem.peopleapi.domain.contact.DTO.ContactDTO;
import com.fabiojuchem.peopleapi.domain.contact.DTO.PersistContactDTO;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersistPersonDTO;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;
import com.fabiojuchem.peopleapi.domain.person.PersonRepository;
import javassist.NotFoundException;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

import static com.fabiojuchem.peopleapi.infrastructure.dictionary.MessageDictionary.PERSON_NOT_FOUND;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO getPersonByName(String name) {
        var person = personRepository.findPersonByName(name);
        if (person.isPresent()) {
            var foundPerson = person.get();
            return new PersonDTO(foundPerson);
        }
        return null;
    }

    public PersonDTO save(PersistPersonDTO personDTO) {
        var person = Person.of(personDTO.getName(), personDTO.getDocument(), personDTO.getBirthDate());
        personDTO.getContacts().forEach(
                contact -> person.addContact(Contact.of(contact.getName(), contact.getPhoneNumber(), contact.getEmail(), person))
        );
        return new PersonDTO(personRepository.save(person));
    }

    public ContactDTO addContact(PersistContactDTO contactDTO, UUID personId) throws NotFoundException {
        var person = personRepository.getOne(personId);
        if (!Objects.isNull(person)) {
            return createAndSaveContact(contactDTO, person);
        }
        throw new NotFoundException(PERSON_NOT_FOUND);
    }

    @Transactional
    private ContactDTO createAndSaveContact(PersistContactDTO contactDTO, Person person) {
        var contact = Contact.of(contactDTO.getName(), contactDTO.getPhoneNumber(), contactDTO.getEmail(), person);
        person.addContact(contact);
        personRepository.save(person);
        return new ContactDTO(contact);
    }

    @Transactional
    public void deletePerson(UUID personId) throws NotFoundException {
        var person = personRepository.getOne(personId);
        if (!Objects.isNull(person)) {
            personRepository.delete(person);
        } else {
            throw new NotFoundException(PERSON_NOT_FOUND);
        }
    }
}
