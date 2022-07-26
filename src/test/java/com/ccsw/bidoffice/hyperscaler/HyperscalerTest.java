package com.ccsw.bidoffice.hyperscaler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyRepository;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

@ExtendWith(MockitoExtension.class)
public class HyperscalerTest {

    @Mock
    private HyperscalerRepository hyperscalerRepository;

    @Mock
    private OfferDataTechnologyRepository offerDataRepository;

    @InjectMocks
    private HyperscalerServiceImpl hyperscalerServiceImpl;

    @Test
    public void getAllFromHyperscaleShouldReturnAllDataFromHyperscaler() {

        List<HyperscalerEntity> data = new ArrayList<>();

        data.add(mock(HyperscalerEntity.class));
        data.add(mock(HyperscalerEntity.class));
        data.add(mock(HyperscalerEntity.class));
        data.add(mock(HyperscalerEntity.class));

        when(hyperscalerRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(data);

        List<HyperscalerEntity> list = hyperscalerServiceImpl.getAllDataFromHyperscaler();
        assertNotNull(list);
        assertEquals(4, list.size());

    }

    private static final long EXISTS_ITEM_ID = 1L;

    @Test
    public void deleteExistsItemIdShouldDelete() {
        this.hyperscalerServiceImpl.deleteItemFromHyperscaler(EXISTS_ITEM_ID);

        verify(this.hyperscalerRepository).deleteById(EXISTS_ITEM_ID);
    }

    @Test
    public void checkIfThereAreOffersShouldReturnTrue() {
        List<OfferDataTechnologyEntity> offerData = new ArrayList<>();

        offerData.add(mock(OfferDataTechnologyEntity.class));

        when(this.offerDataRepository.findAllByHyperscalerId(EXISTS_ITEM_ID)).thenReturn(offerData);

        boolean results = hyperscalerServiceImpl.getDataWithOffersFromHyperscaler(EXISTS_ITEM_ID);

        assertNotNull(results);
        assertEquals(true, results);
    }

    public static final long NOT_EXISTS_ITEM_ID = 0L;

    @Test
    public void checkIfThereAreNotOffersShouldReturnFalse() {
        List<OfferDataTechnologyEntity> offerData = new ArrayList<>();

        when(this.offerDataRepository.findAllByHyperscalerId(NOT_EXISTS_ITEM_ID)).thenReturn(offerData);
        boolean results = hyperscalerServiceImpl.getDataWithOffersFromHyperscaler(NOT_EXISTS_ITEM_ID);

        assertNotNull(results);
        assertEquals(false, results);

    }

}
