package com.ccsw.bidoffice.opportunitytype;

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
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OpportunityTypeIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/opportunitytype/";

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 4;

    ParameterizedTypeReference<List<OpportunityTypeDto>> responseTypeOpportunityType = new ParameterizedTypeReference<List<OpportunityTypeDto>>() {
    };

    @Test
    public void shouldReturnListOpportunityTypeOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OpportunityTypeDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOpportunityType);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_OPPORTUNITY_TYPE, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(OpportunityTypeDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }
}
