package com.ccsw.bidoffice.offerdatateam;

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

import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamEntity;

@ExtendWith(MockitoExtension.class)
public class OfferDataTeamTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferDataTeamRepository offerDataTeamRepository;

    @InjectMocks
    private OfferDataTeamServiceImpl offerDataTeamServiceImpl;

    @Test
    public void findOfferDataTeamNotExistIdOfferShouldEmpty() {

        OfferDataTeamEntity offerDataTeam = null;

        when(this.offerDataTeamRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerDataTeam);

        assertNull(this.offerDataTeamServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerDataTeamRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferDataTeamExistIdOfferShouldOfferDataTeam() {

        OfferDataTeamEntity offerDataTeamEntity = mock(OfferDataTeamEntity.class);

        when(this.offerDataTeamRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerDataTeamEntity);

        OfferDataTeamEntity offerDataTeamResponse = this.offerDataTeamServiceImpl.findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerDataTeamResponse);
        assertEquals(offerDataTeamResponse, offerDataTeamEntity);
        verify(this.offerDataTeamRepository).findByOfferId(ID_OFFER_EXIST);
    }
}
