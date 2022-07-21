package com.ccsw.bidoffice.offerdataproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferDataProjectIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offerdataproject/";

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Test
    public void findOfferDataProjectNotExistIdOfferShouldEmpty() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<OfferDataFileDto> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferDataProject/" + ID_OFFER_NOT_EXIST, HttpMethod.GET,
                httpEntity, OfferDataFileDto.class);

        assertNull(response.getBody());

    }

    @Test
    public void findOfferDataProjectExistIdOfferShouldOfferDataProject() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<OfferDataFileDto> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferDataProject/" + ID_OFFER_EXIST, HttpMethod.GET, httpEntity,
                OfferDataFileDto.class);

        assertNotNull(response.getBody());
        assertEquals(ID_OFFER_EXIST, response.getBody().getId());

    }
}
