package com.ccsw.bidoffice.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.offer.model.ModifyStatusDto;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferItemListDto;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;
import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusDto;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.sector.model.SectorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/offer/";

    public static final Integer TOTAL_DISTINCT_CLIENTS = 4;

    public static final Integer EMPTY_DATA = 0;

    public static final Integer TOTAL_OFFER = 5;

    public static final String CLIENT_CONTAINING = "user";

    public static final String CLIENT_NOT_CONTAINING = "admin";

    public static final Long ID_EXIST = 1L;

    public static final Long ID_NOT_EXIST = 0L;

    private OfferSearchDto offerSearchDto;

    private OfferDto offerDto;

    private SectorDto sectorDto;

    private OpportunityStatusDto opportunityStatusDto;

    private OpportunityTypeDto opportunityTypeDto;

    private ModifyStatusDto modifyStatusDto;

    ParameterizedTypeReference<Page<OfferItemListDto>> responseTypePage = new ParameterizedTypeReference<Page<OfferItemListDto>>() {
    };

    ParameterizedTypeReference<List<String>> responseTypeListClients = new ParameterizedTypeReference<List<String>>() {
    };

    ParameterizedTypeReference<List<OpportunityStatusDto>> responseTypeOpportunityStatus = new ParameterizedTypeReference<List<OpportunityStatusDto>>() {
    };

    @BeforeEach
    public void setUp() {

        offerSearchDto = new OfferSearchDto();

        offerDto = new OfferDto();
        sectorDto = new SectorDto();

        sectorDto.setId(ID_EXIST);
        sectorDto.setName("Otros");
        sectorDto.setPriority(2);

        opportunityStatusDto = new OpportunityStatusDto();
        opportunityStatusDto.setId(ID_EXIST);

        opportunityTypeDto = new OpportunityTypeDto();
        opportunityTypeDto.setId(ID_EXIST);
        opportunityTypeDto.setName("Otros");
        opportunityTypeDto.setPriority(1);

        OfferChangeStatusDto offerChangeStatusDto = new OfferChangeStatusDto();
        modifyStatusDto = new ModifyStatusDto();

        offerChangeStatusDto.setUsername("aelmouss");
        offerChangeStatusDto.setDate(LocalDate.of(2022, 8, 15));
        modifyStatusDto.setChangeStatus(offerChangeStatusDto);
    }

    @Test
    public void findPageShouldReturnPageUser() {

        offerSearchDto.setPageable(PageRequest.of(0, 10));

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

    @Test
    public void findOfferNotExistIdOfferShouldEmpty() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<OfferDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + ID_NOT_EXIST,
                HttpMethod.GET, httpEntity, OfferDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void findOfferExistIdOfferShouldOffer() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<OfferDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + ID_EXIST,
                HttpMethod.GET, httpEntity, OfferDto.class);

        assertNotNull(response.getBody());
        assertEquals(ID_EXIST, response.getBody().getId());

    }

    @Test
    public void saveOfferWithInvalidDataShouldThrowException() {

        HttpEntity<?> httpEntity = new HttpEntity<>(offerDto, getHeaders());
        ResponseEntity<OfferDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                httpEntity, OfferDto.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void saveNewOfferShouldOffer() {

        offerDto.setClient("new client");
        offerDto.setName("new name");
        offerDto.setRequestedDate(LocalDate.of(2022, 8, 15));
        offerDto.setSector(sectorDto);
        offerDto.setOpportunityStatus(opportunityStatusDto);
        offerDto.setOpportunityType(opportunityTypeDto);

        HttpEntity<?> httpEntity = new HttpEntity<>(offerDto, getHeaders());
        ResponseEntity<OfferDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                httpEntity, OfferDto.class);

        offerSearchDto.setPageable(PageRequest.of(0, 10));

        httpEntity = new HttpEntity<>(offerSearchDto, getHeaders());

        ResponseEntity<Page<OfferItemListDto>> listOffer = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertEquals(TOTAL_OFFER + 1, listOffer.getBody().getContent().size());
        assertEquals(true,
                listOffer.getBody().getContent().stream().anyMatch(item -> item.getName().equals(offerDto.getName())));
    }

    @Test
    public void modifyOfferWithExistIdShouldOffer() {

        offerDto.setId(ID_EXIST);
        offerDto.setClient("new client");
        offerDto.setName("new name");
        offerDto.setRequestedDate(LocalDate.of(2022, 8, 15));
        offerDto.setSector(sectorDto);
        offerDto.setOpportunityStatus(opportunityStatusDto);
        offerDto.setOpportunityType(opportunityTypeDto);

        HttpEntity<?> httpEntity = new HttpEntity<>(offerDto, getHeaders());
        ResponseEntity<OfferDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                httpEntity, OfferDto.class);

        offerSearchDto.setPageable(PageRequest.of(0, 10));

        httpEntity = new HttpEntity<>(offerSearchDto, getHeaders());
        ResponseEntity<Page<OfferItemListDto>> listOffer = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertEquals(TOTAL_OFFER, listOffer.getBody().getContent().size());
        assertEquals(ID_EXIST, listOffer.getBody().getContent().stream().filter(item -> item.getId() == ID_EXIST)
                .findFirst().get().getId());
    }

    @Test
    public void modifyOfferWithNotExistIdShouldThrowException() {

        offerDto.setId(ID_NOT_EXIST);
        offerDto.setClient("new client");
        offerDto.setName("new name");
        offerDto.setRequestedDate(LocalDate.of(2022, 8, 15));
        offerDto.setSector(sectorDto);
        offerDto.setOpportunityStatus(opportunityStatusDto);
        offerDto.setOpportunityType(opportunityTypeDto);

        HttpEntity<?> httpEntity = new HttpEntity<>(offerDto, getHeaders());
        ResponseEntity<OfferDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                httpEntity, OfferDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void modifyStatusWithNotValidStatusChangeShouldThrowException() {

        modifyStatusDto.setOpportunityStatus(opportunityStatusDto);
        modifyStatusDto.getChangeStatus().setOpportunityStatus(opportunityStatusDto);
        modifyStatusDto.setId(ID_EXIST);

        HttpEntity<?> httpEntity = new HttpEntity<>(modifyStatusDto, getHeaders());
        ResponseEntity<OfferItemListDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/status",
                HttpMethod.PUT, httpEntity, OfferItemListDto.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());

    }

    @Test
    public void modifyStatusWithValidStatusChangeShouldOffer() {

        opportunityStatusDto.setId(2L);
        opportunityStatusDto.setName("Desestimada");
        modifyStatusDto.setOpportunityStatus(opportunityStatusDto);
        modifyStatusDto.getChangeStatus().setOpportunityStatus(opportunityStatusDto);
        modifyStatusDto.setId(ID_EXIST);

        HttpEntity<?> httpEntity = new HttpEntity<>(modifyStatusDto, getHeaders());
        ResponseEntity<OfferItemListDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "status",
                HttpMethod.PUT, httpEntity, OfferItemListDto.class);

        assertNotNull(response);

        httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<OfferDto> offer = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + ID_EXIST,
                HttpMethod.GET, httpEntity, OfferDto.class);

        assertNotNull(response);
        assertEquals(opportunityStatusDto.getName(), offer.getBody().getOpportunityStatus().getName());
        assertEquals(opportunityStatusDto.getName(), offer.getBody().getChangeStatus().stream()
                .filter(item -> item.getId() == ID_EXIST).findFirst().get().getOpportunityStatus().getName());
    }

}
