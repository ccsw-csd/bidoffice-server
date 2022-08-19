package com.ccsw.bidoffice.offering;

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
import com.ccsw.bidoffice.offering.model.OfferingDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferingIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offering/";
    

    public static final Integer TOTAL_OFFERING = 4;
    private static final long EXISTING_OFFERING_ID = 1L;
    private static final long NOT_EXISTING_OFFERING_ID = 3L;


    ParameterizedTypeReference<List<OfferingDto>> responseTypeOffering = new ParameterizedTypeReference<List<OfferingDto>>() {
    };

    @Test
    public void findPageShouldReturnListOfferingOrderByPriority() {
    	HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OfferingDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeOffering);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_OFFERING, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(OfferingDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }
    
    
    @Test
    public void IfExistsOffersShouldNotDelete() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_OFFERING_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void IfNotExistsOffersShouldDelete() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_OFFERING_ID, HttpMethod.DELETE, httpEntity,
                Void.class);

        ResponseEntity<List<OfferingDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOffering);

        assertNotNull(response);
        assertNotNull(responseAfter);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseAfter.getStatusCode());
        assertEquals(3, responseAfter.getBody().size());
    }
    
    @Test
    public void IfAllAreCorrectShouldEdit() {
    	OfferingDto offeringDto = new OfferingDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(offeringDto, getHeaders());

        offeringDto.setId(1L);
        offeringDto.setName("Name");
        offeringDto.setPriority(15L);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        ResponseEntity<List<OfferingDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOffering);

        OfferingDto editedOfferingDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("Name")).findFirst().orElse(null);

        assertNotNull(editedOfferingDto);
        assertEquals("Name", editedOfferingDto.getName());
        assertEquals(15L, editedOfferingDto.getPriority());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void IfNameExistsShouldNotEdit() {
        OfferingDto offeringDto = new OfferingDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(offeringDto, getHeaders());

        offeringDto.setId(1L);
        offeringDto.setName("Otros3");
        offeringDto.setPriority(1L);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
    
    @Test
    public void IfPriorityExistsShouldNotEdit() {
    	OfferingDto offeringDto = new OfferingDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(offeringDto, getHeaders());

        offeringDto.setId(1L);
        offeringDto.setName("Name 1");
        offeringDto.setPriority(3L);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
    
    @Test
    public void NewItemWithAttributtesCorrectShouldCreate() {
    	OfferingDto offeringDto = new OfferingDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(offeringDto, getHeaders());

        offeringDto.setName("New Name");
        offeringDto.setPriority(13L);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<OfferingDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOffering);

        OfferingDto newOfferingDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("New Name")).findFirst().orElse(null);

        assertEquals(5, responseAfter.getBody().size());
        assertEquals("New Name", newOfferingDto.getName());
        assertEquals(13L, newOfferingDto.getPriority());
    }

    @Test
    public void NewItemWithExistingNameShouldNotCreate() {
    	OfferingDto offeringDto = new OfferingDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(offeringDto, getHeaders());

        offeringDto.setName("Otros");
        offeringDto.setPriority(6L);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void NewItemWithExistingPriorityShouldNotCreate() {
    	OfferingDto offeringDto = new OfferingDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(offeringDto, getHeaders());

        offeringDto.setName("Name 2");
        offeringDto.setPriority(1L);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
