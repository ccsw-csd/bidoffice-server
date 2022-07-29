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

}
