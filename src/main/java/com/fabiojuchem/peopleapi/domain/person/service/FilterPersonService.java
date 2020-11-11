package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;
import com.fabiojuchem.peopleapi.domain.person.QPerson;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonFilterDTO;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonSpecification;
import com.querydsl.core.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FilterPersonService extends QuerydslRepositorySupport {

    @Autowired
    public FilterPersonService() { super(Person.class); }

    public Page<PersonDTO> getAllFiltered(PersonFilterDTO filter, Pageable pageable) {
        var pagination = customPagination(filter, pageable);
        var results = findPersons(filter, pagination);
        var persons = results.getResults().stream()
                .map(PersonDTO::new).collect(Collectors.toList());
        return new PageImpl<>(persons, pageable, results.getTotal());
    }

    private QueryResults<Person> findPersons(PersonFilterDTO filter, Pageable pagination) {
        var predicate = PersonSpecification.filter(filter);
        var query = from(QPerson.person).where(predicate);

        return super.getQuerydsl().applyPagination(pagination, query).fetchResults();
    }

    private Pageable customPagination(PersonFilterDTO filter, Pageable pageable) {
        var sortBy = Sort.Order.asc(filter.getOrderBy());
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
    }
}
