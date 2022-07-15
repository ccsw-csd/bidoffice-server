package com.ccsw.bidoffice.offering;

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
import com.ccsw.bidoffice.offering.model.OfferingDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferingIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offering/";

    public static final Integer TOTAL_OFFERING = 4;

    ParameterizedTypeReference<List<OfferingDto>> responseTypeOffering = new ParameterizedTypeReference<List<OfferingDto>>() {
    };

    @Test
    public void findPageShouldReturnListOfferingOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OfferingDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeOffering);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_OFFERING, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(OfferingDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

}
