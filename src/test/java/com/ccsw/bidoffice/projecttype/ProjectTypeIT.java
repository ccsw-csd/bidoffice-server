package com.ccsw.bidoffice.projecttype;

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
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectTypeIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/projecttype/";

    public static final Integer TOTAL_PROJECT_TYPE = 4;

    private static final Long EXISTING_PROJECTTYPE_ID = 1L;
    private static final Long NOT_EXISTING_PROJECTTYPE_ID = 4L;
    private static final Long NOT_EXISTS_ID_PROJECTTYPE = 5L;

    ParameterizedTypeReference<List<ProjectTypeDto>> responseTypeProjectType = new ParameterizedTypeReference<List<ProjectTypeDto>>() {
    };

    @Test
    public void shouldReturnListProjectTypeOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<ProjectTypeDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeProjectType);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_PROJECT_TYPE, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(ProjectTypeDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));

    }

    @Test
    public void ifUsingProjectTypeInOfferShouldNotDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_PROJECTTYPE_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void IfNotUsingProjectTypeInOfferShouldDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_PROJECTTYPE_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        ResponseEntity<List<ProjectTypeDto>> responseList = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeProjectType);

        assertNotNull(response);
        assertNotNull(responseList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseList.getStatusCode());
        assertEquals(3, responseList.getBody().size());
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() {

        ProjectTypeDto dto = new ProjectTypeDto();
        dto.setId(NOT_EXISTS_ID_PROJECTTYPE);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                ProjectTypeDto.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void modifyWithExistIdShouldModifyProjectType() {
        ProjectTypeDto dto = new ProjectTypeDto();
        dto.setId(EXISTING_PROJECTTYPE_ID);
        dto.setName("pepe");
        dto.setPriority(10);

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                new HttpEntity<>(dto, getHeaders()), ProjectTypeDto.class);

        ResponseEntity<List<ProjectTypeDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeProjectType);
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().size());
        System.out.println(response.getBody().stream().filter(item -> item.getId().equals(EXISTING_PROJECTTYPE_ID)).findFirst().orElse(null).getName());

        ProjectTypeDto projectTypeSearch = response.getBody().stream().filter(item -> item.getId().equals(EXISTING_PROJECTTYPE_ID))
                .findFirst().orElse(null);
        assertNotNull(projectTypeSearch);
        assertEquals("pepe", projectTypeSearch.getName());
    }

}
