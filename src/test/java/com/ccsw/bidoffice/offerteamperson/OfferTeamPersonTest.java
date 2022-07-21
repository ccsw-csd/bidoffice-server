package com.ccsw.bidoffice.offerteamperson;

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

import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonEntity;

@ExtendWith(MockitoExtension.class)
public class OfferTeamPersonTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferTeamPersonRepository offerTeamPersonRepository;

    @InjectMocks
    private OfferTeamPersonServiceImpl offerTeamPersonServiceImpl;

    @Test
    public void findOfferDataFileNotExistIdOfferShouldEmpty() {

        List<OfferTeamPersonEntity> offerTeamPersonEntity = null;

        when(this.offerTeamPersonRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerTeamPersonEntity);

        assertNull(this.offerTeamPersonServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerTeamPersonRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferDataFileExistIdOfferShouldOfferDataFile() {

        List<OfferTeamPersonEntity> offerTeamPersonEntity = new ArrayList<>();
        offerTeamPersonEntity.add(mock(OfferTeamPersonEntity.class));

        when(this.offerTeamPersonRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerTeamPersonEntity);

        List<OfferTeamPersonEntity> offerTeamPersonResponse = this.offerTeamPersonServiceImpl
                .findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerTeamPersonResponse);
        assertEquals(offerTeamPersonResponse, offerTeamPersonEntity);
        verify(this.offerTeamPersonRepository).findByOfferId(ID_OFFER_EXIST);

    }

}
