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

    /*
     * @Test public void deleteWithExistsIdShouldDeleteCategory() {
     * 
     * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_CATEGORY_ID,
     * HttpMethod.DELETE, null, Void.class);
     * 
     * ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST
     * + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
     * assertNotNull(response); assertEquals(2, response.getBody().size()); }
     * 
     */

    private final static long DELETE_ITEM_ID = 1L;

    @Test
    public void deleteWithExistsIdShouldDeleteCategory() {
        HttpEntity<?> httpEntity = new HttpEntity<>(this.hyperscaleDto, getHeaders());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_ITEM_ID, HttpMethod.DELETE, httpEntity,
                Void.class);

        ResponseEntity<List<HyperscalerDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeHyperscaler);

        assertNotNull(response);
        assertEquals(2, response.getBody().size());

    }

    /*
     * @Test public void deleteWithNotExistsIdShouldInternalError() {
     * 
     * ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port +
     * SERVICE_PATH + NEW_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);
     * 
     * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); }
     * 
     */

    private final static long NEW_ITEM_ID = 10L;

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {
        HttpEntity<?> httpEntity = new HttpEntity<>(this.hyperscaleDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NEW_ITEM_ID,
                HttpMethod.DELETE, httpEntity, Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}