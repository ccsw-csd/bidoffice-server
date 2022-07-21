package com.ccsw.bidoffice.offeroffering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.offeroffering.model.OfferOfferingDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferOfferingIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offeroffering/";

    public static final Integer TOTAL_OFFER_OFFERING = 2;

    public static final Integer EMPTY_DATA = 0;

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    ParameterizedTypeReference<List<OfferOfferingDto>> responseTypeListOffeOffering = new ParameterizedTypeReference<List<OfferOfferingDto>>() {
    };

    @Test
    public void findOfferOfferingFileNotExistIdOfferShouldEmpty() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OfferOfferingDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferOffering/" + ID_OFFER_NOT_EXIST, HttpMethod.GET, httpEntity,
                responseTypeListOffeOffering);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_DATA, response.getBody().size());

    }

    @Test
    public void findOfferOfferingFileExistIdOfferShouldOfferOffering() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OfferOfferingDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferOffering/" + ID_OFFER_EXIST, HttpMethod.GET, httpEntity,
                responseTypeListOffeOffering);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_OFFER_OFFERING, response.getBody().size());

    }

}
