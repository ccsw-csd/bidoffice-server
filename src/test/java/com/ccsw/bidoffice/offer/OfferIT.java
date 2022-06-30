package com.ccsw.bidoffice.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offer/";

    public static final Integer TOTAL_OFFER = 2;

    private OfferSearchDto offerSearchDto;

    ParameterizedTypeReference<Page<OfferDto>> responseTypePage = new ParameterizedTypeReference<Page<OfferDto>>() {
    };

    @BeforeEach
    public void setUp() {

        offerSearchDto = new OfferSearchDto();
    }

    @Test
    public void findPageShouldReturnPageUser() {

        offerSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(offerSearchDto, getHeaders());

        ResponseEntity<Page<OfferDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_OFFER, response.getBody().getContent().size());
    }

}
