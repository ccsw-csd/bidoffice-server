package com.ccsw.bidoffice.offertracing;

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

import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;

@ExtendWith(MockitoExtension.class)
public class OfferTracingTest {

    public static final Long ID_OFFER_EXIST = 1L;

    public static final Long ID_OFFER_NOT_EXIST = 0L;

    @Mock
    private OfferTracingRepository offerTracingRepository;

    @InjectMocks
    private OfferTracingServiceImpl offerTracingServiceImpl;

    @Test
    public void findOfferTracingNotExistIdOfferShouldEmpty() {

        List<OfferTracingEntity> offerTracingEntity = null;

        when(this.offerTracingRepository.findByOfferId(ID_OFFER_NOT_EXIST)).thenReturn(offerTracingEntity);

        assertNull(this.offerTracingServiceImpl.findByOfferId(ID_OFFER_NOT_EXIST));
        verify(this.offerTracingRepository).findByOfferId(ID_OFFER_NOT_EXIST);
    }

    @Test
    public void findOfferTracingExistIdOfferShouldOfferTracing() {

        List<OfferTracingEntity> offerTracingEntity = new ArrayList<>();
        offerTracingEntity.add(mock(OfferTracingEntity.class));

        when(this.offerTracingRepository.findByOfferId(ID_OFFER_EXIST)).thenReturn(offerTracingEntity);

        List<OfferTracingEntity> offerTracingResponse = this.offerTracingServiceImpl.findByOfferId(ID_OFFER_EXIST);

        assertNotNull(offerTracingResponse);
        assertEquals(offerTracingResponse, offerTracingEntity);
        verify(this.offerTracingRepository).findByOfferId(ID_OFFER_EXIST);
    }
}
