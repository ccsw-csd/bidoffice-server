package com.ccsw.bidoffice.offering;

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
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offering.model.OfferingDto;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.offeroffering.OfferOfferingServiceImpl;
import com.ccsw.bidoffice.offering.model.OfferingEntity;

@ExtendWith(MockitoExtension.class)
public class OfferingTest {

    @Mock
    private OfferingRepository offeringRepository;
    
    @Mock
    private OfferOfferingServiceImpl offerOfferingServiceImpl;

    @InjectMocks
    private OfferingServiceImpl offeringServiceImpl;
    
    public static final Integer TOTAL_OFFERING = 1;
    
    private static final String NOT_EXISTING_NAME = "Test 1";
    private static final Long NOT_EXISTING_PRIORITY = 18L;

    private static final long EXISTS_ITEM_ID = 1L;
    private static final long NOT_EXISTS_ITEM_ID = 15L;

    private static final String EXISTS_NAME = "Otros";
    private static final Long EXISTS_PRIORITY = 1L;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<OfferingEntity> list = new ArrayList<>();
        list.add(mock(OfferingEntity.class));

        when(offeringRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<OfferingEntity> offerings = offeringServiceImpl.findAllOfferingOrderPriority();

        assertNotNull(offerings);
        assertEquals(TOTAL_OFFERING, offerings.size());

    }

    @Test
    public void deleteExistsItemIdShouldDelete() throws AlreadyExistsException {

        when(this.offerOfferingServiceImpl.checkExistsByOfferingId(EXISTS_ITEM_ID)).thenReturn(false);

        this.offeringServiceImpl.delete(EXISTS_ITEM_ID);

        verify(this.offeringRepository).deleteById(EXISTS_ITEM_ID);
    }

    @Test
    public void deleteIfNotExistsItemIdShouldRiseException() throws AlreadyExistsException {
        when(this.offerOfferingServiceImpl.checkExistsByOfferingId(NOT_EXISTS_ITEM_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> offeringServiceImpl.delete(NOT_EXISTS_ITEM_ID));

        verify(this.offeringRepository, never()).deleteById(NOT_EXISTS_ITEM_ID);

    }

    @Test
    public void saveNewItemWhenAttributesDontMatchShouldSave() throws AlreadyExistsException, EntityNotFoundException {
        OfferingDto offeringDto = new OfferingDto();
        offeringDto.setName(NOT_EXISTING_NAME);
        offeringDto.setPriority(NOT_EXISTING_PRIORITY);

        ArgumentCaptor<OfferingEntity> offeringEntity = ArgumentCaptor.forClass(OfferingEntity.class);

        this.offeringServiceImpl.save(offeringDto);

        verify(this.offeringRepository).save(offeringEntity.capture());

        assertEquals(NOT_EXISTING_NAME, offeringEntity.getValue().getName());
        assertEquals(NOT_EXISTING_PRIORITY, offeringEntity.getValue().getPriority());
    }

    @Test
    public void saveNewItemWhenNameMatchesShouldNotSave() throws AlreadyExistsException, EntityNotFoundException {
        OfferingDto offeringDto = new OfferingDto();
        offeringDto.setName(EXISTS_NAME);
        offeringDto.setPriority(NOT_EXISTING_PRIORITY);

        OfferingEntity offeringEntity = mock(OfferingEntity.class);

        this.offeringServiceImpl.save(offeringDto);

        when(this.offeringRepository.existsByName(EXISTS_NAME)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> offeringServiceImpl.save(offeringDto));

        verify(this.offeringRepository, never()).save(offeringEntity);

    }

    @Test
    public void saveNewItemWhenPriorityMatchesShouldNotSave() throws AlreadyExistsException, EntityNotFoundException {
        OfferingDto offeringDto = new OfferingDto();
        offeringDto.setName(NOT_EXISTING_NAME);
        offeringDto.setPriority(EXISTS_PRIORITY);

        OfferingEntity offeringEntity = mock(OfferingEntity.class);

        this.offeringServiceImpl.save(offeringDto);

        when(this.offeringRepository.existsByPriority(EXISTS_PRIORITY)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> offeringServiceImpl.save(offeringDto));

        verify(this.offeringRepository, never()).save(offeringEntity);

    }

    @Test
    public void editOfferingWhenNamesAreTheSameAndPriorityAlreadyExists() throws AlreadyExistsException {
        OfferingDto offeringDto = new OfferingDto();
        offeringDto.setId(EXISTS_ITEM_ID);
        offeringDto.setName(EXISTS_NAME);
        offeringDto.setPriority(EXISTS_PRIORITY);

        OfferingEntity offeringEntity = mock(OfferingEntity.class);

        when(this.offeringRepository.existsByIdIsNotAndPriority(EXISTS_ITEM_ID, EXISTS_PRIORITY)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> offeringServiceImpl.save(offeringDto));

        verify(this.offeringRepository, never()).save(offeringEntity);

    }

    @Test
    public void editOfferingWhenPrioritiesAreTheSameAndNameAlreadyExists() throws AlreadyExistsException {
        OfferingDto offeringDto = new OfferingDto();
        offeringDto.setId(EXISTS_ITEM_ID);
        offeringDto.setName(EXISTS_NAME);
        offeringDto.setPriority(EXISTS_PRIORITY);

        OfferingEntity offeringEntity = mock(OfferingEntity.class);

        when(this.offeringRepository.existsByIdIsNotAndName(EXISTS_ITEM_ID, EXISTS_NAME)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> offeringServiceImpl.save(offeringDto));

        verify(this.offeringRepository, never()).save(offeringEntity);

    }

    @Test
    public void editOfferingsWhenAttributesDontExistShouldEdit()
            throws AlreadyExistsException, EntityNotFoundException {
        OfferingDto offeringDto = new OfferingDto();
        offeringDto.setId(EXISTS_ITEM_ID);
        offeringDto.setName(NOT_EXISTING_NAME);
        offeringDto.setPriority(NOT_EXISTING_PRIORITY);

        OfferingEntity offeringEntity = mock(OfferingEntity.class);

        when(this.offeringRepository.existsByIdIsNotAndName(EXISTS_ITEM_ID, NOT_EXISTING_NAME)).thenReturn(false);

        when(this.offeringRepository.existsByIdIsNotAndPriority(EXISTS_ITEM_ID, NOT_EXISTING_PRIORITY))
                .thenReturn(false);

        when(this.offeringRepository.findById(EXISTS_ITEM_ID)).thenReturn(Optional.of(offeringEntity));

        this.offeringServiceImpl.save(offeringDto);

        verify(this.offeringRepository).save(offeringEntity);

    }
}
