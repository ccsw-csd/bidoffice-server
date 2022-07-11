package com.ccsw.bidoffice.masterdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.common.model.BaseClassDto;
import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MasterDataIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/masterdata/";

    public static final Integer TOTAL_DATA_PRIORITY = 4;

    public static final Integer TOTAL_PERSON_ACTIVE = 2;

    public static final Integer TOTAL_DISTINCT_CLIENTS = 4;

    public static final Integer EMPTY_DATA = 0;

    public static final String USERNAME_PERSON_ACTIVE = "aelmouss";

    public static final String USERNAME_PERSON_NOT_ACTIVE = "jopepe";

    public static final String USERNAME_PERSON_NOT_EXIST = "juanxa";

    public static final String NAME_PERSON_ACTIVE = "Ayoub";

    public static final String LASTNAME_PERSON_ACTIVE = "El Moussaoui";

    public static final String CLIENT_CONTAINING = "user";

    public static final String CLIENT_NOT_CONTAINING = "admin";

    ParameterizedTypeReference<List<BaseClassDto>> responseTypeBaseClass = new ParameterizedTypeReference<List<BaseClassDto>>() {
    };

    ParameterizedTypeReference<Page<PersonDto>> responseTypePagePerson = new ParameterizedTypeReference<Page<PersonDto>>() {
    };

    ParameterizedTypeReference<List<String>> responseTypeListClients = new ParameterizedTypeReference<List<String>>() {
    };

    private HttpEntity<?> httpEntity;

    private ResponseEntity<Page<PersonDto>> response;

    private ResponseEntity<List<BaseClassDto>> responseOrderPriority;

    private ResponseEntity<List<String>> responseDistinctClients;

    private PersonSearchDto personSearchDto;

    @BeforeEach
    public void setUp() {
        personSearchDto = new PersonSearchDto();
        httpEntity = new HttpEntity<>(getHeaders());
    }

    @Test
    public void findPageShouldReturnPagePerson() {

        httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "persons", HttpMethod.POST, httpEntity,
                responseTypePagePerson);

        assertEquals(3, response.getBody().getContent().size());
        assertTrue(response.getBody().getContent().stream().allMatch(PersonDto::getActive));
    }

    @Test
    public void findPageShouldReturnFilteredPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_ACTIVE);
        personSearchDto.setName(NAME_PERSON_ACTIVE);
        personSearchDto.setLastname(LASTNAME_PERSON_ACTIVE);

        httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "persons", HttpMethod.POST, httpEntity,
                responseTypePagePerson);

        assertEquals(1, response.getBody().getContent().size());
        assertEquals(USERNAME_PERSON_ACTIVE, response.getBody().getContent().get(0).getUsername());
    }

    @Test
    public void findPageWithUsernameNotActiveShouldReturnEmptyPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_NOT_ACTIVE);
        personSearchDto.setName(NAME_PERSON_ACTIVE);
        personSearchDto.setLastname(LASTNAME_PERSON_ACTIVE);

        httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "persons", HttpMethod.POST, httpEntity,
                responseTypePagePerson);

        assertNotNull(response.getBody().getContent());
        assertEquals(EMPTY_DATA, response.getBody().getContent().size());
    }

    @Test
    public void findPageWithNotExistUsernameNotShouldReturnEmptyPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_NOT_EXIST);
        personSearchDto.setName(NAME_PERSON_ACTIVE);
        personSearchDto.setLastname(LASTNAME_PERSON_ACTIVE);

        httpEntity = new HttpEntity<>(personSearchDto, getHeaders());

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "persons", HttpMethod.POST, httpEntity,
                responseTypePagePerson);

        assertNotNull(response.getBody().getContent());
        assertEquals(EMPTY_DATA, response.getBody().getContent().size());
    }

    @Test
    public void findClientShouldReturnAllDistinctsClients() {

        responseDistinctClients = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "clients/" + CLIENT_CONTAINING, HttpMethod.GET, httpEntity,
                responseTypeListClients);

        assertNotNull(responseDistinctClients.getBody());
        assertEquals(TOTAL_DISTINCT_CLIENTS, responseDistinctClients.getBody().size());

    }

    @Test
    public void findClientShouldReturnEmptyClients() {

        responseDistinctClients = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "clients/" + CLIENT_NOT_CONTAINING, HttpMethod.GET, httpEntity,
                responseTypeListClients);

        assertNotNull(responseDistinctClients.getBody());
        assertEquals(EMPTY_DATA, responseDistinctClients.getBody().size());

    }

    @Nested
    @DisplayName("Order priority")
    class OrderPriority extends BaseITAbstract {

        @AfterEach
        public void verify() {

            assertNotNull(responseOrderPriority.getBody());
            assertEquals(TOTAL_DATA_PRIORITY, responseOrderPriority.getBody().size());
            assertTrue(responseOrderPriority.getBody().stream().sorted(Comparator.comparing(BaseClassDto::getPriority))
                    .collect(Collectors.toList()).equals(responseOrderPriority.getBody()));
        }

        @Test
        public void shouldReturnListSectorOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "sectors", HttpMethod.GET,
                    httpEntity, responseTypeBaseClass);
        }

        @Test
        public void shouldReturnListOpportunityTypeOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "types", HttpMethod.GET,
                    httpEntity, responseTypeBaseClass);
        }

        @Test
        public void shouldReturnListOpportunityStatusOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "status", HttpMethod.GET,
                    httpEntity, responseTypeBaseClass);
        }

        @Test
        public void shouldReturnListFileTypeOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "fileTypes", HttpMethod.GET,
                    httpEntity, responseTypeBaseClass);
        }

        @Test
        public void shouldReturnListHyperscalerOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "hyperscalers",
                    HttpMethod.GET, httpEntity, responseTypeBaseClass);
        }

        @Test
        public void shouldReturnListMethodologyOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "methodologys",
                    HttpMethod.GET, httpEntity, responseTypeBaseClass);
        }

        @Test
        public void findPageShouldReturnListOfferingOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "offerings", HttpMethod.GET,
                    httpEntity, responseTypeBaseClass);
        }

        @Test
        public void shouldReturnListProjectTypesOrderByPriority() {

            responseOrderPriority = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "projectTypes",
                    HttpMethod.GET, httpEntity, responseTypeBaseClass);
        }
    }

}
