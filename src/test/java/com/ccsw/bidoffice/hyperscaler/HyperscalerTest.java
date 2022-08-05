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

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.delete(NOT_EXISTS_ITEM_ID));

        verify(this.hyperscalerRepository, never()).deleteById(NOT_EXISTS_ITEM_ID);

    }

    private static final String NOT_EXISTING_NAME = "Test 1";
    private static final Long NOT_EXISTING_PRIORITY = 100L;

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

    private static final String EXISTING_NAME = "Name exists";
    private static final Long EXISTING_PRIORITY = 1L;

    @Test
    public void saveNewItemWhenNameMatchesShouldNotSave() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setName(EXISTING_NAME);
        hyperscalerDto.setPriority(NOT_EXISTING_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        when(hyperscalerServiceImpl.checkIfExistsName(EXISTING_NAME)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void saveNewItemWhenPriorityMatchesShouldNotSave() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setName(NOT_EXISTING_NAME);
        hyperscalerDto.setPriority(EXISTING_PRIORITY);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        when(hyperscalerServiceImpl.checkIfExistsPriority(EXISTING_PRIORITY)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    private static final Long EXISTS_HYPERSCALER_ID = 2L;
    // private static final Long EXISTING_PRIORITY = 1L;

    @Test
    public void editHyperscalerWhenNamesAreTheSameAndPriorityAlreadyExists() throws AlreadyExistsException {

        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Test 1");
        hyperscalerDto.setPriority(1L);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.findById(1L)).thenReturn(Optional.of(hyperscalerEntity));

        when(hyperscalerEntity.getName()).thenReturn("Test 1");

        when(hyperscalerServiceImpl.checkIfExistsPriority(1L)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalerWhenPrioritiesAreTheSameAndNameAlreadyExists() throws AlreadyExistsException {

        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name already exists");
        hyperscalerDto.setPriority(2L);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.findById(1L)).thenReturn(Optional.of(hyperscalerEntity));
        when(hyperscalerEntity.getName()).thenReturn("Google Cloud");

        when(hyperscalerEntity.getPriority()).thenReturn(2L);

        when(hyperscalerServiceImpl.checkIfExistsName("Name already exists")).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalerWhenAttributesAreDifferentAndNameAlreadyExists() throws AlreadyExistsException {

        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name already exists");
        hyperscalerDto.setPriority(2L);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.findById(1L)).thenReturn(Optional.of(hyperscalerEntity));
        when(hyperscalerEntity.getName()).thenReturn("Google Cloud");
        when(hyperscalerEntity.getPriority()).thenReturn(3L);

        when(hyperscalerServiceImpl.checkIfExistsPriority(2L)).thenReturn(false);
        when(hyperscalerServiceImpl.checkIfExistsName("Name already exists")).thenReturn(true);

        assertThrows(AlreadyExistsException.class,
                () -> hyperscalerServiceImpl.checkWhenAttributesAreDifferent(hyperscalerDto));

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalerWhenAttributesAreDifferentAndPriorityAlreadyExists() throws AlreadyExistsException {

        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name doesn't exist");
        hyperscalerDto.setPriority(2L);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.findById(1L)).thenReturn(Optional.of(hyperscalerEntity));
        when(hyperscalerEntity.getName()).thenReturn("Google Cloud");
        when(hyperscalerEntity.getPriority()).thenReturn(3L);

        when(hyperscalerServiceImpl.checkIfExistsPriority(2L)).thenReturn(true);

        assertThrows(AlreadyExistsException.class,
                () -> hyperscalerServiceImpl.checkWhenAttributesAreDifferent(hyperscalerDto));

        assertThrows(AlreadyExistsException.class, () -> hyperscalerServiceImpl.saveItem(hyperscalerDto));

        verify(this.hyperscalerRepository, never()).save(hyperscalerEntity);

    }

    @Test
    public void editHyperscalersWhenAttributesDontExistShouldEdit() throws AlreadyExistsException {
        HyperscalerDto hyperscalerDto = new HyperscalerDto();
        hyperscalerDto.setId(1L);
        hyperscalerDto.setName("Name Edited");
        hyperscalerDto.setPriority(5L);

        HyperscalerEntity hyperscalerEntity = mock(HyperscalerEntity.class);

        when(this.hyperscalerRepository.findById(1L)).thenReturn(Optional.of(hyperscalerEntity));
        when(hyperscalerEntity.getName()).thenReturn("Google Cloud");
        when(hyperscalerEntity.getPriority()).thenReturn(3L);

        when(hyperscalerServiceImpl.checkIfExistsPriority(5L)).thenReturn(false);
        when(hyperscalerServiceImpl.checkIfExistsName("Name Edited")).thenReturn(false);

        this.hyperscalerServiceImpl.saveItem(hyperscalerDto);

        verify(this.hyperscalerRepository).save(hyperscalerEntity);

    }

}
