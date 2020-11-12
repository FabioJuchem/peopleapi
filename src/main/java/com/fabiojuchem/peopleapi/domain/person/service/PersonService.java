package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.contact.Contact;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersistPersonDTO;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;
import com.fabiojuchem.peopleapi.domain.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
