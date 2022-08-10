package com.ccsw.bidoffice.technology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import com.ccsw.bidoffice.technology.model.TechnologyDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TechnologyIT extends BaseITAbstract {

    private static final String SERVICE_PATH = "/technology/";
    private static final Integer TOTAL_TECHNOLOGIES = 3;
    private static final Long EXISTING_TECHNOLOGY_ID = 1L;
    private static final Long NOT_EXISTING_TECHNOLOGY_ID = 3L;

    private TechnologyDto technologyDto;

    ParameterizedTypeReference<List<TechnologyDto>> responseTypeTechnology = new ParameterizedTypeReference<List<TechnologyDto>>() {
    };

    /**
     * Antes de cada test, crea un objeto TechnologyDto en blanco.
     */
    @BeforeEach
    public void setUp() {
        this.technologyDto = new TechnologyDto();
    }

    /**
     * Recupera todas las tecnologías registradas en la base de datos.
     * 
     * Debe devolver tres tecnologías.
     */
    @Test
    public void findAllTechnologiesShouldReturnListOrderedByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(this.technologyDto, getHeaders());

        ResponseEntity<List<TechnologyDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        assertNotNull(response);
        assertEquals(TOTAL_TECHNOLOGIES, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(TechnologyDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

    /**
     * Intenta borrar una tecnología que está siendo utilizada en alguna oferta.
     * 
     * La tecnología no debe poder ser borrada. Al intentar borrarla, el
     * ResponseEntity debe devolver un estado HTTP 409 (conflicto).
     */
    @Test
    public void ifUsingTechnologyInOfferShouldNotDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_TECHNOLOGY_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Intenta borrar una tecnología que NO está siendo utilizada en alguna oferta.
     * 
     * La tecnología debe ser borrada. Al borrarla, el ResponseEntity debe devolver
     * un estado HTTP 200 (OK).
     */
    @Test
    public void IfNotUsingTechnologyInOfferShouldDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_TECHNOLOGY_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        ResponseEntity<List<TechnologyDto>> responseList = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        assertNotNull(response);
        assertNotNull(responseList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseList.getStatusCode());
        assertEquals(2, responseList.getBody().size());
    }

}
