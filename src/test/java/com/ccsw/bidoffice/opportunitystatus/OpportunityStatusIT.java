package com.ccsw.bidoffice.opportunitystatus;

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
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OpportunityStatusIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/opportunitystatus/";

    public static final Integer TOTAL_OPPORTUNITY_STATUS = 4;

    ParameterizedTypeReference<List<OpportunityStatusDto>> responseTypeOpportunityStatus = new ParameterizedTypeReference<List<OpportunityStatusDto>>() {
    };

    @Test
    public void shouldReturnListOpportunityStatusOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OpportunityStatusDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeOpportunityStatus);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_OPPORTUNITY_STATUS, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(OpportunityStatusDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }
}
