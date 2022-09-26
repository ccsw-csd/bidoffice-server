package com.ccsw.bidoffice.hyperscaler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HyperscalerIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/hyperscaler/";

    ParameterizedTypeReference<List<HyperscalerDto>> responseTypeHyperscaler = new ParameterizedTypeReference<List<HyperscalerDto>>() {
    };

    private HyperscalerDto hyperscaleDto;

    @BeforeEach
    public void setUp() {
        this.hyperscaleDto = new HyperscalerDto();
    }

    @Test
    public void getAllFromHyperscaleShouldReturnAllData() {

        HttpEntity<?> httpEntity = new HttpEntity<>(this.hyperscaleDto, getHeaders());

        ResponseEntity<List<HyperscalerDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());

    }

    private static final long EXISTING_HYPERSCALER_ID = 1L;

    @Test
    public void IfExistsOffersShouldNotDelete() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_HYPERSCALER_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    private static final long NOT_EXISTING_HYPERSCALER_ID = 2L;

    @Test
    public void UnlessExistsOffersShouldDelete() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_HYPERSCALER_ID, HttpMethod.DELETE, httpEntity,
                Void.class);

        ResponseEntity<List<HyperscalerDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        assertNotNull(response);
        assertNotNull(responseAfter);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseAfter.getStatusCode());
        assertEquals(2, responseAfter.getBody().size());
    }

    @Test
    public void IfAttributesAreCorrectShouldEdit() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Correct Name");
        hyperscalerDto.setPriority(48);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        ResponseEntity<List<HyperscalerDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        HyperscalerDto editedHyperscalerDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("Correct Name")).findFirst().orElse(null);

        assertNotNull(editedHyperscalerDto);
        assertEquals("Correct Name", editedHyperscalerDto.getName());
        assertEquals(48, editedHyperscalerDto.getPriority());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void IfNameExistsShouldNotEdit() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name 3");
        hyperscalerDto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void IfNameDoesNotExistShouldEdit() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name edited");
        hyperscalerDto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<HyperscalerDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        HyperscalerDto editedHyperscalerDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("Name edited")).findFirst().orElse(null);

        assertEquals("Name edited", editedHyperscalerDto.getName());
        assertEquals(1, editedHyperscalerDto.getPriority());

    }

    @Test
    public void IfPriorityExistsShouldNotEdit() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name 1");
        hyperscalerDto.setPriority(3);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void IfPriorityDoesNotExistShouldEdit() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name 1");
        hyperscalerDto.setPriority(4);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<HyperscalerDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        HyperscalerDto editedHyperscalerDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("Name 1")).findFirst().orElse(null);

        assertEquals("Name 1", editedHyperscalerDto.getName());
        assertEquals(4, editedHyperscalerDto.getPriority());

    }

    @Test
    public void NewItemWithAttributtesCorrectShouldCreate() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setName("New Name");
        hyperscalerDto.setPriority(4);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<HyperscalerDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        HyperscalerDto newHyperscalerDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("New Name")).findFirst().orElse(null);

        assertEquals(4, responseAfter.getBody().size());
        assertEquals("New Name", newHyperscalerDto.getName());
        assertEquals(4, newHyperscalerDto.getPriority());
    }

    @Test
    public void NewItemWithExistingNameShouldNotCreate() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setName("Name 1");
        hyperscalerDto.setPriority(4);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void NewItemWithExistingPriorityShouldNotCreate() {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(hyperscalerDto, getHeaders());

        hyperscalerDto.setName("New Name");
        hyperscalerDto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
