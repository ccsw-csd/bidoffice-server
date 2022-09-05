package com.ccsw.bidoffice.sector;

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
import com.ccsw.bidoffice.sector.model.SectorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SectorIT extends BaseITAbstract {

    private static final String SERVICE_PATH = "/sector/";
    private static final Integer TOTAL_SECTOR = 4;
    private static final Long EXISTING_SECTOR_ID = 2L;
    private static final Long NOT_EXISTING_SECTOR_ID = 3L;

    ParameterizedTypeReference<List<SectorDto>> responseTypeSector = new ParameterizedTypeReference<List<SectorDto>>() {
    };

    /**
     * Recupera todos los sectores de la Base de Datos.
     * 
     * El test debe devolver 4 sectores.
     */
    @Test
    public void shouldReturnListSectorOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<SectorDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeSector);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_SECTOR, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(SectorDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

    /**
     * Intenta borrar un sector que está siendo utilizado en alguna oferta.
     * 
     * El sector no debe poder ser borrado. Al intentar borrarlo, el ResponseEntity
     * debe devolver un estado HTTP 409 (conflicto).
     */
    @Test
    public void ifUsingSectorInOfferShouldNotDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_SECTOR_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void ifNotUsingSectorInOfferShouldDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_SECTOR_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        ResponseEntity<List<SectorDto>> responseList = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeSector);

        assertNotNull(response);
        assertNotNull(responseList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseList.getStatusCode());
        assertEquals(3, responseList.getBody().size());
    }
}
