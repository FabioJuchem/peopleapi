package com.fabiojuchem.peopleapi.presentation.resource;

import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonResource {

    private PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("api/v1/person")
    public PersonDTO getPersonByName(@RequestParam String name) {
        return personService.getPersonByName(name);
    }

}
