package com.ccsw.bidoffice.offerdatachapter;

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

import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterEntity;

@ExtendWith(MockitoExtension.class)
public class OfferDataChapterTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferDataChapterRepository offerDataChapterRepository;

    @InjectMocks
    private OfferDataChapterServiceImpl offerDataChapterServiceImpl;

    @Test
    public void findOfferDataChapterNotExistIdOfferShouldEmpty() {

        OfferDataChapterEntity chapterEntity = null;

        when(this.offerDataChapterRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(chapterEntity);

        assertNull(this.offerDataChapterServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerDataChapterRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferDataChapterExistIdOfferShouldOfferDataChapter() {

        OfferDataChapterEntity offerChapterEntity = mock(OfferDataChapterEntity.class);

        when(this.offerDataChapterRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerChapterEntity);

        OfferDataChapterEntity offerChapterResponse = this.offerDataChapterServiceImpl.findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerChapterResponse);
        assertEquals(offerChapterResponse, offerChapterEntity);
        verify(this.offerDataChapterRepository).findByOfferId(ID_OFFER_EXIST);

    }
}
