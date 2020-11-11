package com.fabiojuchem.peopleapi.domain.person;

import com.fabiojuchem.peopleapi.infrastructure.querydsl.QueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID>, QueryDslRepository<Person> {

    Optional<Person> findPersonByName(String name);
}
