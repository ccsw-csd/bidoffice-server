package com.ccsw.bidoffice.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.ccsw.bidoffice.person.model.PersonSearchDto;

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

    private PersonSearchDto personSearchDto;

    @BeforeEach
    public void setUp() {

        personSearchDto = new PersonSearchDto();
    }

    @Test
    public void findPageShouldReturnPagePerson() {

        HttpEntity<?> httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findFilter",
                HttpMethod.POST, httpEntity, responseTypeListPerson);

        assertEquals(3, response.getBody().size());
        assertTrue(response.getBody().stream().allMatch(PersonDto::getActive));
    }

    @Test
    public void findPageShouldReturnFilteredPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_ACTIVE);
        personSearchDto.setName(NAME_PERSON_ACTIVE);
        personSearchDto.setLastname(LASTNAME_PERSON_ACTIVE);

        HttpEntity<?> httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findFilter",
                HttpMethod.POST, httpEntity, responseTypeListPerson);

        assertEquals(1, response.getBody().size());
        assertEquals(USERNAME_PERSON_ACTIVE, response.getBody().get(0).getUsername());
    }

    @Test
    public void findPageWithUsernameNotActiveShouldReturnEmptyPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_NOT_ACTIVE);
        personSearchDto.setName(NAME_PERSON_ACTIVE);
        personSearchDto.setLastname(LASTNAME_PERSON_ACTIVE);

        HttpEntity<?> httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findFilter",
                HttpMethod.POST, httpEntity, responseTypeListPerson);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_PERSON, response.getBody().size());
    }

    @Test
    public void findPageWithNotExistUsernameNotShouldReturnEmptyPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_NOT_EXIST);
        personSearchDto.setName(NAME_PERSON_ACTIVE);
        personSearchDto.setLastname(LASTNAME_PERSON_ACTIVE);

        HttpEntity<?> httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findFilter",
                HttpMethod.POST, httpEntity, responseTypeListPerson);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_PERSON, response.getBody().size());
    }
}
