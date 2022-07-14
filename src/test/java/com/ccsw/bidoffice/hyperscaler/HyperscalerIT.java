package com.ccsw.bidoffice.hyperscaler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HyperscalerIT extends BaseITAbstract {

    @Autowired
    private TestRestTemplate restTemplate;

    public static final String LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    public static final String SERVICE_PATH = "/hyperscaler";

    ParameterizedTypeReference<List<HyperscalerDto>> responseTypeList = new ParameterizedTypeReference<List<HyperscalerDto>>() {
    };

    private HyperscalerDto hyperscaleDto;

    @BeforeEach
    public void setUp() {
        this.hyperscaleDto = new HyperscalerDto();
    }

    @Test
    public void getAllFromHyperscaleShouldReturnAllData() {

        HttpEntity<?> httpEntity = new HttpEntity<>(this.hyperscaleDto, getHeaders());

        ResponseEntity<List<HyperscalerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());

    }
}
