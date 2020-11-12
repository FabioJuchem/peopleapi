package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.person.DTO.PersonDTO;
import com.fabiojuchem.peopleapi.domain.person.Person;
import com.fabiojuchem.peopleapi.domain.person.QPerson;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonFilterDTO;
import com.fabiojuchem.peopleapi.domain.person.filter.PersonSpecification;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterPersonService extends QuerydslRepositorySupport {

    @Autowired
    public FilterPersonService() { super(Person.class); }

    @Transactional
    public Page<PersonDTO> getAllFiltered(PersonFilterDTO filter, Pageable pageable) {
        Pageable pagination = customPagination(filter, pageable);
        QueryResults<Person> results = findPersons(filter, pagination);
        List<PersonDTO> persons = results.getResults().stream()
                .map(PersonDTO::new).collect(Collectors.toList());
        return new PageImpl<>(persons, pageable, results.getTotal());
    }

    private QueryResults<Person> findPersons(PersonFilterDTO filter, Pageable pagination) {
        BooleanExpression predicate = PersonSpecification.filter(filter);
        JPQLQuery query = from(QPerson.person).where(predicate);

        return super.getQuerydsl().applyPagination(pagination, query).fetchResults();
    }

    private Pageable customPagination(PersonFilterDTO filter, Pageable pageable) {
        Sort.Order sortBy = Sort.Order.asc(filter.getOrderBy());
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
    }
}
