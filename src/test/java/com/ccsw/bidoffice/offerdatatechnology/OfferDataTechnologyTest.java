package com.ccsw.bidoffice.offerdatatechnology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

@ExtendWith(MockitoExtension.class)
public class OfferDataTechnologyTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferDataTechnologyRepository offerDataTechnologyRepository;

    @InjectMocks
    private OfferDataTechnologyServiceImpl offerDataTechnologyServiceImpl;

    @Test
    public void findOfferDataTechnologyNotExistIdOfferShouldEmpty() {

        OfferDataTechnologyEntity offDataTechnologyEntity = null;

        when(this.offerDataTechnologyRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offDataTechnologyEntity);

        assertNull(this.offerDataTechnologyServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerDataTechnologyRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferDataTechnologyExistIdOfferShouldOfferDataProject() {

        OfferDataTechnologyEntity offerDataTechnologyEntity = mock(OfferDataTechnologyEntity.class);

        when(this.offerDataTechnologyRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerDataTechnologyEntity);

        OfferDataTechnologyEntity offerDataTechnologyResponse = this.offerDataTechnologyServiceImpl
                .findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerDataTechnologyResponse);
        assertEquals(offerDataTechnologyResponse, offerDataTechnologyEntity);
        verify(this.offerDataTechnologyRepository).findByOfferId(ID_OFFER_EXIST);
    }
}
