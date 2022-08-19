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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.sector.model.SectorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SectorIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/sector/";

    public static final Integer TOTAL_SECTOR = 4;

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
}
