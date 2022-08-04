package com.ccsw.bidoffice.hyperscaler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HyperscalerTest {

    @Mock
    private HyperscalerRepository hyperscalerRepository;

    @Mock
    private OfferDataTechnologyServiceImpl offerDataServiceImpl;

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
    public void deleteExistsItemIdShouldDelete() throws AlreadyExistsException {

        when(this.offerDataServiceImpl.checkExistsByHyperscalerId(EXISTS_ITEM_ID)).thenReturn(false);

        this.hyperscalerServiceImpl.delete(EXISTS_ITEM_ID);

        verify(this.hyperscalerRepository).deleteById(EXISTS_ITEM_ID);
    }

    private static final long NOT_EXISTS_ITEM_ID = 0L;

    @Test
    public void deleteIfNotExistsItemIdShouldRiseException() throws AlreadyExistsException {
        when(this.offerDataServiceImpl.checkExistsByHyperscalerId(NOT_EXISTS_ITEM_ID)).thenReturn(true);

<<<<<<< Updated upstream
        assertThrows(AlreadyExistsException.class,
                () -> hyperscalerServiceImpl.delete(NOT_EXISTS_ITEM_ID));
=======
        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.delete(NOT_EXISTS_ITEM_ID));
>>>>>>> Stashed changes

        verify(this.hyperscalerRepository, never()).deleteById(NOT_EXISTS_ITEM_ID);
        ;
    }

}
