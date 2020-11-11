package com.fabiojuchem.peopleapi.domain.person.filter;

import com.fabiojuchem.peopleapi.domain.person.QPerson;
import com.fabiojuchem.peopleapi.infrastructure.querydsl.BooleanExpressionBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;

import static com.fabiojuchem.peopleapi.domain.person.QPerson.*;

public class PersonSpecification {

    private PersonSpecification() {
    }

    public static BooleanExpression filter(PersonFilterDTO filter) {
        return BooleanExpressionBuilder.builder()
                .expression(filter.hasName(), () -> byName(filter.getName()))
                .expression(filter.hasDocument(), () -> byDocument(filter.getDocument()))
                .expression(filter.hasBirthDate(), () -> byBirthDate(filter.getBirthDate()))
                .build();

    }

    private static BooleanExpression byName(String name) {
        return person.name.equalsIgnoreCase(name);
    }

    private static BooleanExpression byDocument(String document) {
        return person.document.eq(document);
    }

    private static BooleanExpression byBirthDate(LocalDate birthDate) {
        return person.birthDate.eq(birthDate);
    }
}
