package com.ccsw.bidoffice.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.person.model.PersonDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/person/";

    public static final Integer EMPTY_PERSON = 0;

    public static final String USERNAME_PERSON_ACTIVE = "aelmouss";

    public static final String USERNAME_PERSON_NOT_ACTIVE = "jopepe";

    public static final String USERNAME_PERSON_NOT_EXIST = "juanxa";

    public static final String NAME_PERSON_ACTIVE = "Ayoub";

    public static final String LASTNAME_PERSON_ACTIVE = "El Moussaoui";

    ParameterizedTypeReference<List<PersonDto>> responseTypeListPerson = new ParameterizedTypeReference<List<PersonDto>>() {
    };

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void findPersonsShouldReturnFilteredListPerson() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + USERNAME_PERSON_ACTIVE, HttpMethod.GET, httpEntity,
                responseTypeListPerson);

        assertNotNull(response.getBody().size());
        assertEquals(true,
                response.getBody().stream().allMatch(item -> item.getUsername().contains(USERNAME_PERSON_ACTIVE)));
    }

    @Test
    public void findPersonsWithUsernameNotActiveShouldReturnEmptyListPersons() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + USERNAME_PERSON_NOT_ACTIVE, HttpMethod.GET, httpEntity,
                responseTypeListPerson);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_PERSON, response.getBody().size());
    }

    @Test
    public void findPageWithNotExistUsernameNotShouldReturnEmptyPerson() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + USERNAME_PERSON_NOT_EXIST, HttpMethod.GET, httpEntity,
                responseTypeListPerson);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_PERSON, response.getBody().size());
    }
}