package com.ccsw.bidoffice.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.InvalidDataException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offer.model.Clients;
import com.ccsw.bidoffice.offer.model.ModifyStatusDto;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;
import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusDto;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.sector.model.SectorDto;

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

    @Mock
    private BeanMapper beanMapper;

    private OfferSearchDto offerSearchDto;

    private OfferDto offerDto;

    private ModifyStatusDto modifyStatusDto;

    private OpportunityStatusDto opportunityStatusDto;

    @BeforeEach
    public void setUp() {

        offerSearchDto = new OfferSearchDto();
        offerSearchDto.setPageable(PageRequest.of(0, 5));
        opportunityStatusDto = new OpportunityStatusDto();
        opportunityStatusDto.setName("Desestimada");

        offerDto = new OfferDto();
        offerDto.setClient("new client");
        offerDto.setName("new name");
        offerDto.setRequestedDate(LocalDate.of(2022, 8, 15));
        offerDto.setSector(new SectorDto());
        offerDto.setOpportunityStatus(opportunityStatusDto);
        offerDto.setOpportunityType(new OpportunityTypeDto());

        opportunityStatusDto = new OpportunityStatusDto();
        opportunityStatusDto.setName("Entregada");
        OfferChangeStatusDto offerChangeStatusDto = new OfferChangeStatusDto();
        offerChangeStatusDto.setOpportunityStatus(opportunityStatusDto);
        offerChangeStatusDto.setDate(LocalDateTime.of(2022, 8, 15, 0, 0));
        offerChangeStatusDto.setUsername("aelmouss");
        modifyStatusDto = new ModifyStatusDto();
        modifyStatusDto.setChangeStatus(offerChangeStatusDto);
        modifyStatusDto.setOpportunityStatus(opportunityStatusDto);

    }

    @Test
    public void findPageShouldReturnOfferPage() throws InvalidDataException {

        List<OfferEntity> offers = new ArrayList<>();
        offers.add(mock(OfferEntity.class));

        when(offerRepository.findAll(any(), eq(offerSearchDto.getPageable())))
                .thenReturn(new PageImpl<>(offers, offerSearchDto.getPageable(), offers.size()));

        Page<OfferEntity> page = offerServiceImpl.findPage(offerSearchDto);

        assertNotNull(page);
        assertEquals(offers.size(), page.getContent().size());

    }

    @Test
    public void findPageWithInvalidDateShouldThrowException() {

        offerSearchDto.setStartDateModification(LocalDateTime.now().plusDays(1));
        offerSearchDto.setEndDateModification(LocalDateTime.now());

        assertThrows(InvalidDataException.class, () -> offerServiceImpl.findPage(offerSearchDto));

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
    public void findOfferNotExistIdOfferShouldEmpty() throws EntityNotFoundException {

        when(this.offerRepository.findById(ID_OFFER_NOT_EXIST)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> offerServiceImpl.getOffer(ID_OFFER_NOT_EXIST));

    }

    @Test
    public void findOfferExistIdOfferShouldOffer() throws EntityNotFoundException {

        OfferEntity offerEntity = mock(OfferEntity.class);

        when(this.offerRepository.findById(ID_OFFER_EXIST)).thenReturn(Optional.of(offerEntity));
        OfferEntity offerResponse = this.offerServiceImpl.getOffer(ID_OFFER_EXIST);

        assertNotNull(offerResponse);
        assertEquals(offerResponse, offerEntity);
        verify(this.offerRepository).findById(ID_OFFER_EXIST);

    }

    @Test
    public void saveOfferWithInvalidDataShouldThrowException() {

        offerDto.setSector(null);

        assertThrows(InvalidDataException.class, () -> offerServiceImpl.save(offerDto));

    }

    @Test
    public void saveNewOfferShouldOffer() throws InvalidDataException, EntityNotFoundException {

        OfferEntity offerEntity = mock(OfferEntity.class);

        when(this.beanMapper.map(offerDto, OfferEntity.class)).thenReturn(offerEntity);
        offerServiceImpl.save(offerDto);

        verify(this.offerRepository).save(offerEntity);
    }

    @Test
    public void modifyOfferWithExistIdShouldOffer() throws InvalidDataException, EntityNotFoundException {

        offerDto.setId(ID_OFFER_EXIST);
        OfferEntity offerEntity = mock(OfferEntity.class);

        when(offerRepository.existsById(ID_OFFER_EXIST)).thenReturn(true);
        when(offerRepository.findById(ID_OFFER_EXIST)).thenReturn(Optional.of(offerEntity));
        when(this.beanMapper.map(offerDto, OfferEntity.class)).thenReturn(offerEntity);
        offerServiceImpl.save(offerDto);

        verify(this.offerRepository).save(offerEntity);
    }

    @Test
    public void modifyOfferWithNotExistIdShouldThrowException() {

        offerDto.setId(ID_OFFER_NOT_EXIST);
        when(offerRepository.existsById(ID_OFFER_NOT_EXIST)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> offerServiceImpl.save(offerDto));

    }

    @Test
    public void modifyStatusWithNotValidStatusChangeShouldThrowException() {

        modifyStatusDto.setId(ID_OFFER_EXIST);
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerRepository.findById(ID_OFFER_EXIST)).thenReturn(Optional.of(offerEntity));
        when(offerEntity.getOpportunityStatus()).thenReturn(mock(OpportunityStatusEntity.class));
        when(offerEntity.getOpportunityStatus().getName()).thenReturn("Entregada");

        assertThrows(InvalidDataException.class, () -> offerServiceImpl.modifyStatus(modifyStatusDto));
        verify(this.offerRepository, never()).save(offerEntity);
    }

    public void modifyStatusWithValidStatusChangeShould() throws InvalidDataException, EntityNotFoundException {

        modifyStatusDto.setId(ID_OFFER_EXIST);
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerRepository.findById(ID_OFFER_EXIST)).thenReturn(Optional.of(offerEntity));
        when(offerEntity.getOpportunityStatus()).thenReturn(mock(OpportunityStatusEntity.class));
        when(offerEntity.getOpportunityStatus().getName()).thenReturn("En curso");

        assertNotNull(offerServiceImpl.modifyStatus(modifyStatusDto));
        verify(this.offerRepository).save(offerEntity);
    }
}
