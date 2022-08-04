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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.technology.model.TechnologyDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TechnologyIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/technology/";

    public static final Integer TOTAL_TECHNOLOGIES = 1;

    private TechnologyDto technologyDto;

    ParameterizedTypeReference<List<TechnologyDto>> responseTypeTechnology = new ParameterizedTypeReference<List<TechnologyDto>>() {
    };

    @BeforeEach
    public void setUp() {
        this.technologyDto = new TechnologyDto();
    }

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

}
