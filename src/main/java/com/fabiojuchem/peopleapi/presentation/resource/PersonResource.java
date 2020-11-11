package com.fabiojuchem.peopleapi.presentation.resource;

import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonFilterDTO;
import com.fabiojuchem.peopleapi.domain.person.service.FilterPersonService;
import com.fabiojuchem.peopleapi.domain.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<PersonDTO> getAllEmployees(@RequestBody PersonFilterDTO filter, Pageable pageable) {
        return filterPersonService.getAllFiltered(filter, pageable);
    }

}
