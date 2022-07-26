package com.ccsw.bidoffice.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ccsw.bidoffice.offer.model.Clients;
import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

@ExtendWith(MockitoExtension.class)
public class OfferTest {

    public static final Integer TOTAL_CLIENT = 1;

    public static final Integer EMPTY_CLIENT = 0;

    public static final String CLIENT_CONTAINING = "user";

    public static final String CLIENT_NOT_CONTAINING = "admin";

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerServiceImpl;

    private OfferSearchDto offerSearchDto;

    @BeforeEach
    public void setUp() {

        offerSearchDto = new OfferSearchDto();
    }

    @Test
    public void findPageShouldReturnUsersPage() {

        offerSearchDto.setPageable(PageRequest.of(0, 5));

        List<OfferEntity> offers = new ArrayList<>();
        offers.add(mock(OfferEntity.class));

        when(offerRepository.findAll(offerSearchDto.getPageable()))
                .thenReturn(new PageImpl<>(offers, offerSearchDto.getPageable(), offers.size()));

        Page<OfferEntity> page = offerServiceImpl.findPage(offerSearchDto);

        assertNotNull(page);
        assertEquals(offers.size(), page.getContent().size());

    }

    @Test
    public void findClientShouldReturnAllListClients() {

        List<Clients> list = new ArrayList<>();
        list.add(mock(Clients.class));

        when(this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(CLIENT_CONTAINING)).thenReturn(list);

        List<String> clients = this.offerServiceImpl.findFirst15DistinctClientLikeFilter(CLIENT_CONTAINING);

        assertNotNull(clients);
        assertEquals(TOTAL_CLIENT, clients.size());
    }

    @Test
    public void findClientShouldReturnEmptyClients() {

        List<Clients> list = new ArrayList<>();

        when(this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(CLIENT_NOT_CONTAINING))
                .thenReturn(list);

        List<String> clients = this.offerServiceImpl.findFirst15DistinctClientLikeFilter(CLIENT_NOT_CONTAINING);

        assertNotNull(clients);
        assertEquals(EMPTY_CLIENT, clients.size());
    }

    @Test
    public void findOfferNotExistIdOfferShouldEmpty() {

        when(this.offerRepository.findById(ID_OFFER_NOT_EXIST)).thenReturn(Optional.empty());

        assertNull(this.offerServiceImpl.getOffer(ID_OFFER_NOT_EXIST));
        verify(this.offerRepository).findById(ID_OFFER_NOT_EXIST);

    }

    @Test
    public void findOfferExistIdOfferShouldOffer() {

        OfferEntity offerEntity = mock(OfferEntity.class);

        when(this.offerRepository.findById(ID_OFFER_EXIST)).thenReturn(Optional.of(offerEntity));

        OfferEntity offerResponse = this.offerServiceImpl.getOffer(ID_OFFER_EXIST);

        assertNotNull(offerResponse);
        assertEquals(offerResponse, offerEntity);
        verify(this.offerRepository).findById(ID_OFFER_EXIST);

    }

}
