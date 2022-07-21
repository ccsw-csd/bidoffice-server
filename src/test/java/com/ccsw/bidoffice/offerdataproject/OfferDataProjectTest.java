package com.ccsw.bidoffice.offerdataproject;

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

import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectEntity;

@ExtendWith(MockitoExtension.class)
public class OfferDataProjectTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferDataProjectRepository offerDataProjectRepository;

    @InjectMocks
    private OfferDataProjectServiceImpl offerDataProjectServiceImpl;

    @Test
    public void findOfferDataProjectNotExistIdOfferShouldEmpty() {

        OfferDataProjectEntity offerDataProjectEntity = null;

        when(this.offerDataProjectRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerDataProjectEntity);

        assertNull(this.offerDataProjectServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerDataProjectRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferDataProjectExistIdOfferShouldOfferDataProject() {

        OfferDataProjectEntity offerDataProjectEntity = mock(OfferDataProjectEntity.class);

        when(this.offerDataProjectRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerDataProjectEntity);

        OfferDataProjectEntity offerDataProjectResponse = this.offerDataProjectRepository.findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerDataProjectResponse);
        assertEquals(offerDataProjectResponse, offerDataProjectEntity);
        verify(this.offerDataProjectRepository).findByOfferId(ID_OFFER_EXIST);
    }
}
