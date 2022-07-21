package com.ccsw.bidoffice.offeroffering;

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

import com.ccsw.bidoffice.offeroffering.model.OfferOfferingEntity;

@ExtendWith(MockitoExtension.class)
public class OfferOfferingTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferOfferingRepository offerOfferingRepository;

    @InjectMocks
    private OfferOfferingServiceImpl offerOfferingServiceImpl;

    @Test
    public void findOfferOfferingFileNotExistIdOfferShouldEmpty() {

        List<OfferOfferingEntity> offerOfferingEntity = null;

        when(this.offerOfferingRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerOfferingEntity);

        assertNull(this.offerOfferingServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerOfferingRepository).findByOfferId(ID_OFFER_NOT_EXIST);

    }

    @Test
    public void findOfferOfferingFileExistIdOfferShouldOfferOffering() {

        List<OfferOfferingEntity> offerOfferingEntity = new ArrayList<>();
        offerOfferingEntity.add(mock(OfferOfferingEntity.class));

        when(this.offerOfferingRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerOfferingEntity);

        List<OfferOfferingEntity> offerOfferingResponse = this.offerOfferingServiceImpl.findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerOfferingResponse);
        assertEquals(offerOfferingResponse, offerOfferingEntity);
        verify(this.offerOfferingRepository).findByOfferId(ID_OFFER_EXIST);
    }

}
