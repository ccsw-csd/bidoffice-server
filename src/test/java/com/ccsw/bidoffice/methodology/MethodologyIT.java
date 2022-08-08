package com.ccsw.bidoffice.methodology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @Test
    public void saveExistsMethodologyShouldUpdate() {
        MethodologyDto dto = new MethodologyDto();
        dto.setId(EXISTS_ID);
        dto.setName(NEW_NAME);
        dto.setPriority(NEW_PRIORITY);

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTS_ID, HttpMethod.PUT,
                new HttpEntity<>(dto, getHeaders()), Void.class);

        ResponseEntity<List<MethodologyDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeMethodology);
        assertNotNull(response.getBody());
        assertEquals(TOTAL_METHODOLOGY, response.getBody().size());

        MethodologyDto methodologySearch = response.getBody().stream().filter(item -> item.getId().equals(EXISTS_ID))
                .findFirst().orElse(null);
        assertNotNull(methodologySearch);
        assertEquals(NEW_NAME, methodologySearch.getName());
    }
}
