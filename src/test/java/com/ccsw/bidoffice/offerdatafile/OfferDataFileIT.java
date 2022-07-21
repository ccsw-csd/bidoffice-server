package com.ccsw.bidoffice.offerdatafile;

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
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferDataFileIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offerdatafile/";

    public static final Integer TOTAL_DATA = 2;

    public static final Integer EMPTY_DATA = 0;

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    ParameterizedTypeReference<List<OfferDataFileDto>> responseTypeListOffeDataFile = new ParameterizedTypeReference<List<OfferDataFileDto>>() {
    };

    @Test
    public void findOfferDataFileNotExistIdOfferShouldEmpty() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OfferDataFileDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferDataFile/" + ID_OFFER_NOT_EXIST, HttpMethod.GET, httpEntity,
                responseTypeListOffeDataFile);

        assertNotNull(response.getBody());
        assertEquals(EMPTY_DATA, response.getBody().size());

    }

    @Test
    public void findOfferDataFileExistIdOfferShouldOfferDataFile() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<OfferDataFileDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferDataFile/" + ID_OFFER_EXIST, HttpMethod.GET, httpEntity,
                responseTypeListOffeDataFile);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_DATA, response.getBody().size());

    }

}
