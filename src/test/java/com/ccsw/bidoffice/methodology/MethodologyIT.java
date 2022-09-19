package com.ccsw.bidoffice.methodology;

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
import com.ccsw.bidoffice.methodology.model.MethodologyDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MethodologyIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/methodology/";

    public static final Integer TOTAL_METHODOLOGY = 4;
    public static final Long EXISTS_ID = 2L;
    public static final String NEW_NAME = "Otros33";
    public static final Integer NEW_PRIORITY = 10;

    ParameterizedTypeReference<List<MethodologyDto>> responseTypeMethodology = new ParameterizedTypeReference<List<MethodologyDto>>() {
    };

    @Test
    public void shouldReturnListMethodologyOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<MethodologyDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeMethodology);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_METHODOLOGY, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(MethodologyDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

    /**
     * Descripción:
     * 
     * Este test debe actualizar los datos de una metodología ya existente. Debe
     * devolver en posterior consulta el mismo nombre que se había utilizado para
     * actualizar el registro.
     * 
     * Qué fallaba:
     * 
     * Lo especificado en los asertos de salida, no coincidía con las constantes de
     * entrada.
     * 
     */
    @Test
    public void saveExistsMethodologyShouldUpdate() {

        MethodologyDto methodologyDto = new MethodologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(methodologyDto, getHeaders());

        methodologyDto.setId(EXISTS_ID);
        methodologyDto.setName(NEW_NAME);
        methodologyDto.setPriority(NEW_PRIORITY);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        ResponseEntity<List<MethodologyDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeMethodology);

        MethodologyDto editDto = responseAfter.getBody().stream().filter(element -> element.getName().equals(NEW_NAME))
                .findFirst().orElse(null);

        assertNotNull(editDto);
        assertEquals(NEW_NAME, editDto.getName());
        assertEquals(NEW_PRIORITY, editDto.getPriority());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
