package com.ccsw.bidoffice.admin;

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

import com.ccsw.bidoffice.admin.model.HyperscaleDto;
import com.ccsw.bidoffice.config.BaseITAbstract;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminIT extends BaseITAbstract {

    @Autowired
    private TestRestTemplate restTemplate;

    public static final String LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    public static final String SERVICE_PATH = "/admin/hyperscaler";

    ParameterizedTypeReference<List<HyperscaleDto>> responseTypeList = new ParameterizedTypeReference<List<HyperscaleDto>>() {
    };

    private HyperscaleDto hyperscaleDto;

    @BeforeEach
    public void setUp() {
        this.hyperscaleDto = new HyperscaleDto();

        /*
         * this.hyperscaleDto.setId(1L); this.hyperscaleDto.setName("name 1");
         * this.hyperscaleDto.setPriority(1L);
         */
    }

    @Test
    public void getAllFromHyperscaleShouldReturnAllData() {

        HttpEntity<?> httpEntity = new HttpEntity<>(this.hyperscaleDto, getHeaders());

        ResponseEntity<List<HyperscaleDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());

    }
}
