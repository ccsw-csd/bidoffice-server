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
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;
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

    private static final String NOT_EXISTING_NAME = "Test 1";
    private static final Long NOT_EXISTING_PRIORITY = 100L;

    private static final long EXISTS_ITEM_ID = 1L;
    private static final long NOT_EXISTS_ITEM_ID = 0L;

    private static final String EXISTS_NAME = "Name exists";
    private static final Long EXISTS_PRIORITY = 1L;

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

    @Test
    public void deleteExistsItemIdShouldDelete() throws AlreadyExistsException {

        when(this.offerDataServiceImpl.checkExistsByHyperscalerId(EXISTS_ITEM_ID)).thenReturn(false);

        this.hyperscalerServiceImpl.delete(EXISTS_ITEM_ID);

        verify(this.hyperscalerRepository).deleteById(EXISTS_ITEM_ID);
    }

    @Test
    public void deleteIfNotExistsItemIdShouldRiseException() throws AlreadyExistsException {
        when(this.offerDataServiceImpl.checkExistsByHyperscalerId(NOT_EXISTS_ITEM_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.delete(NOT_EXISTS_ITEM_ID));

        verify(this.hyperscalerRepository, never()).deleteById(NOT_EXISTS_ITEM_ID);

    }

    @Test
    public void saveNewItemWhenAttributesDontMatchShouldSave() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setName(NOT_EXISTING_NAME);
        hyperscalerDto.setPriority(NOT_EXISTING_PRIORITY);

        ArgumentCaptor<HyperscalerEntity> hyperscalerEntity = ArgumentCaptor.forClass(HyperscalerEntity.class);

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        verify(this.hyperscalerRepository).save(hyperscalerEntity.capture());

        assertEquals(NOT_EXISTING_NAME, hyperscalerEntity.getValue().getName());
        assertEquals(NOT_EXISTING_PRIORITY, hyperscalerEntity.getValue().getPriority());
    }

    @Test
    public void saveNewItemWhenNameMatchesShouldNotSave() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setName(EXISTS_NAME);
        hyperscalerDto.setPriority(NOT_EXISTING_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        when(this.hyperscalerRepository.existsByName(EXISTS_NAME)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void saveNewItemWhenPriorityMatchesShouldNotSave() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setName(NOT_EXISTING_NAME);
        hyperscalerDto.setPriority(EXISTS_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        when(this.hyperscalerRepository.existsByPriority(EXISTS_PRIORITY)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalerWhenNamesAreTheSameAndPriorityAlreadyExists() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(EXISTS_ITEM_ID);
        hyperscalerDto.setName(EXISTS_NAME);
        hyperscalerDto.setPriority(EXISTS_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.existsByPriorityAndIdIsNot(EXISTS_PRIORITY, EXISTS_ITEM_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalerWhenPrioritiesAreTheSameAndNameAlreadyExists() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(EXISTS_ITEM_ID);
        hyperscalerDto.setName(EXISTS_NAME);
        hyperscalerDto.setPriority(EXISTS_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.existsByNameAndIdIsNot(EXISTS_NAME, EXISTS_ITEM_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalersWhenAttributesDontExistShouldEdit() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(EXISTS_ITEM_ID);
        hyperscalerDto.setName(NOT_EXISTING_NAME);
        hyperscalerDto.setPriority(NOT_EXISTING_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.existsByNameAndIdIsNot(NOT_EXISTING_NAME, EXISTS_ITEM_ID)).thenReturn(false);

        when(this.hyperscalerRepository.existsByPriorityAndIdIsNot(NOT_EXISTING_PRIORITY, EXISTS_ITEM_ID))
                .thenReturn(false);

        when(this.hyperscalerRepository.findById(EXISTS_ITEM_ID)).thenReturn(Optional.of(hyperscalerEntity));

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        verify(this.hyperscalerRepository).save(hyperscalerEntity);

    }

}
