package com.fabiojuchem.peopleapi.presentation.resource;

import com.fabiojuchem.peopleapi.domain.contact.DTO.ContactDTO;
import com.fabiojuchem.peopleapi.domain.contact.DTO.PersistContactDTO;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersistPersonDTO;
import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonFilterDTO;
import com.fabiojuchem.peopleapi.domain.person.service.FilterPersonService;
import com.fabiojuchem.peopleapi.domain.person.service.PersonService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
public class PersonResource {

    private PersonService personService;

    private FilterPersonService filterPersonService;

    @Autowired
    public PersonResource(PersonService personService, FilterPersonService filterPersonService) {
        this.personService = personService;
        this.filterPersonService = filterPersonService;
    }

    @GetMapping("api/v1/person")
    public PersonDTO getPersonByName(@RequestParam String name) {
        return personService.getPersonByName(name);
    }

    @GetMapping(value = "api/v1/person/list", params = { "page", "size" })
    public Page<PersonDTO> getAllPersons(
            @RequestParam String name,
            @RequestParam String document,
            @RequestParam LocalDate birthDate,
            Pageable pageable) {
        return filterPersonService.getAllFiltered(new PersonFilterDTO(name, document, birthDate), pageable);
    }

    @PostMapping(value = "api/v1/person")
    public PersonDTO save(@Valid @RequestBody PersistPersonDTO personDTO) {
        return personService.save(personDTO);
    }

    @PutMapping(value = "api/v1/person/{id}/contact")
    public ContactDTO addContact(
            @Valid @RequestBody PersistContactDTO contactDTO,
            @PathVariable UUID id
            ) throws NotFoundException {
        return personService.addContact(contactDTO, id);
    }

    @DeleteMapping(value = "api/v1/person/{id}")
    public void delete(@PathVariable UUID id) throws NotFoundException {
        personService.deletePerson(id);
    }

}
