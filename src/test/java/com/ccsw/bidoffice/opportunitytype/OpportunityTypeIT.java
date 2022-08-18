package com.ccsw.bidoffice.opportunitytype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OpportunityTypeIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/opportunitytype/";

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 4;

    public static final Long NOT_EXISTING_ID = 2L;

    public static final Long EXISTING_ID = 1L;

    ParameterizedTypeReference<List<OpportunityTypeDto>> responseTypeOpportunityType = new ParameterizedTypeReference<List<OpportunityTypeDto>>() {
    };

    @Test
    public void shouldReturnListOpportunityTypeOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OpportunityTypeDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOpportunityType);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_OPPORTUNITY_TYPE, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(OpportunityTypeDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

    @Test
    public void shouldDeleteIfDoesNotExistAnOffer() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);

        ResponseEntity<List<OpportunityTypeDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOpportunityType);

        assertEquals(3, responseAfter.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void shouldNotDeleteIfExistsAnOffer() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void shouldNotSaveNewItemIfAttributesAreDuplicated() {
        OpportunityTypeDto dto = new OpportunityTypeDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        dto.setName("New name");
        dto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void shouldSaveIfAttributesAreNotDuplicated() {
        OpportunityTypeDto dto = new OpportunityTypeDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        dto.setName("New name");
        dto.setPriority(10);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<OpportunityTypeDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOpportunityType);

        OpportunityTypeDto editedDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("New name")).findFirst().orElse(null);

        assertEquals("New name", editedDto.getName());
        assertEquals(10, editedDto.getPriority());

    }

    @Test
    public void shouldEditIfAttributesAreNotDupplicated() {
        OpportunityTypeDto dto = new OpportunityTypeDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        dto.setId(1L);
        dto.setName("Edited name");
        dto.setPriority(10);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<OpportunityTypeDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOpportunityType);

        OpportunityTypeDto editedDto = responseAfter.getBody().stream().filter(element -> element.getId().equals(1L))
                .findFirst().orElse(null);

        assertEquals("Edited name", editedDto.getName());
        assertEquals(10, editedDto.getPriority());
    }

    @Test
    public void shouldNotEditIfNameExists() {
        OpportunityTypeDto dto = new OpportunityTypeDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        dto.setId(1L);
        dto.setName("Otros3");
        dto.setPriority(10);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void shouldNotEditIfPriorityExists() {
        OpportunityTypeDto dto = new OpportunityTypeDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        dto.setId(1L);
        dto.setName("Edited name");
        dto.setPriority(2);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
