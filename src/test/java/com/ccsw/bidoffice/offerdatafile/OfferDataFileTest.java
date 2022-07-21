package com.ccsw.bidoffice.offerdatafile;

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

import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

@ExtendWith(MockitoExtension.class)
public class OfferDataFileTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferDataFileRepository offerDataFileRepository;

    @InjectMocks
    private OfferDataFileServiceImpl offerDataFileServiceImpl;

    @Test
    public void findOfferDataFileNotExistIdOfferShouldEmpty() {

        List<OfferDataFileEntity> offerDataFile = null;

        when(this.offerDataFileRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerDataFile);

        assertNull(this.offerDataFileServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerDataFileRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferDataFileExistIdOfferShouldOfferDataFile() {

        List<OfferDataFileEntity> offerDataFile = new ArrayList<>();
        offerDataFile.add(mock(OfferDataFileEntity.class));

        when(this.offerDataFileRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerDataFile);

        List<OfferDataFileEntity> offerDataFileResponse = this.offerDataFileServiceImpl
                .findByOfferId(ID_OFFER_NOT_EXIST);

        assertNotNull(offerDataFileResponse);
        assertEquals(offerDataFileResponse, offerDataFile);
        verify(this.offerDataFileRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }
}
