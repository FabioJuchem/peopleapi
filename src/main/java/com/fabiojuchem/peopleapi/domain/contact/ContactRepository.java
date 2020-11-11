package com.fabiojuchem.peopleapi.domain.contact;

import com.fabiojuchem.peopleapi.infrastructure.querydsl.QueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID>, QueryDslRepository<Contact> {
}
