package com.ccsw.bidoffice.offertechnology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyEntity;

@ExtendWith(MockitoExtension.class)
public class OfferTechnologyTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferTechnologyRepository offerTechnologyRepository;

    @InjectMocks
    private OfferTechnologyServiceImpl offerTechnologyServiceImpl;

    @Test
    public void findOfferTechnologyNotExistIdOfferShouldEmpty() {

        List<OfferTechnologyEntity> offerTechnologyEntity = null;

        when(this.offerTechnologyRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerTechnologyEntity);

        assertNull(this.offerTechnologyServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerTechnologyRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferTechnologyExistIdOfferShouldOfferDataFile() {

        List<OfferTechnologyEntity> offerTechnologyEntity = new ArrayList<>();
        offerTechnologyEntity.add(mock(OfferTechnologyEntity.class));

        when(this.offerTechnologyRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerTechnologyEntity);

        List<OfferTechnologyEntity> offerTechnologyResponse = this.offerTechnologyServiceImpl
                .findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerTechnologyResponse);
        assertEquals(offerTechnologyResponse, offerTechnologyEntity);
        verify(this.offerTechnologyRepository).findByOfferId(ID_OFFER_EXIST);

    }
}
