package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.PersonRepository;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<PersonDTO> getAllFiltered(PersonFilterDTO filter, Pageable pageable) {
        var pagination = customPagination(filter, pageable);

    }

    private Pageable customPagination(PersonFilterDTO filter, Pageable pageable) {
        var sortBy = Sort.Order.asc(filter.getOrderBy());
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
    }
}
