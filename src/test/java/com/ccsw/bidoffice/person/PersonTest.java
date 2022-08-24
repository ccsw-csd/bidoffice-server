package com.ccsw.bidoffice.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ccsw.bidoffice.person.model.PersonEntity;

@ExtendWith(MockitoExtension.class)
public class PersonTest {

    public static final Integer TOTAL_PERSON = 1;

    public static final Integer EMPTY_PERSON = 0;

    public static final String USERNAME_PERSON_ACTIVE = "aelmouss";

    public static final String USERNAME_PERSON_NOT_ACTIVE = "jopepe";

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void findPageShouldReturnFilteredPerson() {

        List<PersonEntity> list = new ArrayList<>();
        list.add(mock(PersonEntity.class));

        when(this.personRepository.findAll(any(), eq(PageRequest.of(0, 15))))
                .thenReturn(new PageImpl<>(list, PageRequest.of(0, 15), list.size()));

        List<PersonEntity> persons = this.personServiceImpl.findFirst15Filter(USERNAME_PERSON_ACTIVE);

        assertNotNull(persons);
        assertEquals(TOTAL_PERSON, persons.size());

    }

    @Test
    public void findPageWithUsernameNotActiveShouldReturnEmptyPerson() {

        List<PersonEntity> list = new ArrayList<>();

        when(this.personRepository.findAll(any(), eq(PageRequest.of(0, 15))))
                .thenReturn(new PageImpl<>(list, PageRequest.of(0, 10), list.size()));

        List<PersonEntity> persons = this.personServiceImpl.findFirst15Filter(USERNAME_PERSON_NOT_ACTIVE);

        assertNotNull(persons);
        assertEquals(EMPTY_PERSON, persons.size());
    }
}
