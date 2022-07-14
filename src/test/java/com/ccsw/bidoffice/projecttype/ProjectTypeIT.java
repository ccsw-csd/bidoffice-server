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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectTypeIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/projecttype/";

    public static final Integer TOTAL_PROJECT_TYPE = 4;

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

}
