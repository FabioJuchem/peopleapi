package com.fabiojuchem.peopleapi.domain.person.service;

import com.fabiojuchem.peopleapi.domain.person.filter.PersonFilterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilterPersonServiceTest {

    @Autowired
    private FilterPersonService filterPersonService;

    private PersonFilterDTO personFilterDTO;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        personFilterDTO = new PersonFilterDTO();
    }

    @Test
    void getAllFiltered_newRequest_shouldGetPersonPageable() {
       var pageable = PageRequest.of(0, 10);
       var result = filterPersonService.getAllFiltered(personFilterDTO, pageable);

        assertNotNull(result);
        assertTrue(result.getNumberOfElements() == 0);
    }

}