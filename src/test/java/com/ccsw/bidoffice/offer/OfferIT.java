package com.ccsw.bidoffice.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.offer.model.OfferItemListDto;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offer/";

    public static final Integer TOTAL_DISTINCT_CLIENTS = 4;

    public static final Integer EMPTY_DATA = 0;

    public static final Integer TOTAL_OFFER = 5;

    public static final String CLIENT_CONTAINING = "user";

    public static final String CLIENT_NOT_CONTAINING = "admin";

    private OfferSearchDto offerSearchDto;

    ParameterizedTypeReference<Page<OfferItemListDto>> responseTypePage = new ParameterizedTypeReference<Page<OfferItemListDto>>() {
    };

    ParameterizedTypeReference<List<String>> responseTypeListClients = new ParameterizedTypeReference<List<String>>() {
    };

    @BeforeEach
    public void setUp() {

        offerSearchDto = new OfferSearchDto();
    }

    @Test
    public void findPageShouldReturnPageUser() {

        offerSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(offerSearchDto, getHeaders());

        ResponseEntity<Page<OfferItemListDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_OFFER, response.getBody().getContent().size());
    }

    @Test
    public void findClientShouldReturnAllDistinctsClients() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<String>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "client/" + CLIENT_CONTAINING, HttpMethod.GET, httpEntity,
                responseTypeListClients);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_DISTINCT_CLIENTS, response.getBody().size());

    }

    @Test
    public void findClientShouldReturnEmptyClients() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<String>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "client/" + CLIENT_NOT_CONTAINING, HttpMethod.GET, httpEntity,
                responseTypeListClients);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_DATA, response.getBody().size());

    }

}
