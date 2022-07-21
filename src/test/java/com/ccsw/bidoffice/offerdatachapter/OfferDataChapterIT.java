package com.ccsw.bidoffice.offerdatachapter;

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
import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferDataChapterIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offerdatachapter/";

    public static final Integer TOTAL_DATA = 1;

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Test
    public void findOfferDataChapterNotExistIdOfferShouldEmpty() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<OfferDataChapterDto> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferDataChapter/" + ID_OFFER_NOT_EXIST, HttpMethod.GET,
                httpEntity, OfferDataChapterDto.class);

        assertNull(response.getBody());
    }

    @Test
    public void findOfferDataChapterExistIdOfferShouldOfferDataChapter() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<OfferDataChapterDto> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findOfferDataChapter/" + ID_OFFER_EXIST, HttpMethod.GET, httpEntity,
                OfferDataChapterDto.class);

        assertNotNull(response.getBody());
        assertEquals(ID_OFFER_EXIST, response.getBody().getId());

    }
}
