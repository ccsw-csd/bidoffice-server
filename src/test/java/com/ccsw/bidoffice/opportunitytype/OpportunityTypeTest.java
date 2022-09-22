package com.ccsw.bidoffice.opportunitytype;

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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offer.OfferService;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

@ExtendWith(MockitoExtension.class)
public class OpportunityTypeTest {

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 1;

    public static final Long EXISTING_ID = 1L;
    public static final Long NOT_EXISTING_ID = 2L;

    public static final String NOT_EXISTING_NAME = "Defensa";
    public static final String EXISTS_NAME = "Otros";

    public static final int NOT_EXISTING_PRIORITY = 20;
    public static final int EXISTS_PRIORITY = 1;

    @Mock
    private OpportunityTypeRepository opportunityTypeRepository;

    @Mock
    private OfferService offerService;

    @InjectMocks
    private OpportunityTypeServiceImpl opportunityTypeServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<OpportunityTypeEntity> list = new ArrayList<>();
        list.add(mock(OpportunityTypeEntity.class));

        when(opportunityTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<OpportunityTypeEntity> opportunityType = opportunityTypeServiceImpl.findAllOpportunityTypeOrderPriority();

        assertNotNull(opportunityType);
        assertEquals(TOTAL_OPPORTUNITY_TYPE, opportunityType.size());

    }

    @Test
    public void deleteWithoutAnOfferShouldDelete() throws AlreadyExistsException {
        when(this.offerService.checkIfExistsOffer(NOT_EXISTING_ID)).thenReturn(false);

        this.opportunityTypeServiceImpl.delete(NOT_EXISTING_ID);

        verify(this.opportunityTypeRepository).deleteById(NOT_EXISTING_ID);

    }

    @Test
    public void deleteWithExistingOfferShouldThrowAnError() throws AlreadyExistsException {
        when(this.offerService.checkIfExistsOffer(EXISTING_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> this.opportunityTypeServiceImpl.delete(EXISTING_ID));

        verify(this.opportunityTypeRepository, never()).deleteById(EXISTING_ID);
    }

    @Test
    public void saveNewItemWithoutDuppedAttributesShouldSave() throws AlreadyExistsException, EntityNotFoundException {

        OpportunityTypeDto opportunityTypeDto = new OpportunityTypeDto();
        opportunityTypeDto.setName(NOT_EXISTING_NAME);
        opportunityTypeDto.setPriority(NOT_EXISTING_PRIORITY);

        ArgumentCaptor<OpportunityTypeEntity> opportunityTypeEntity = ArgumentCaptor
                .forClass(OpportunityTypeEntity.class);

        this.opportunityTypeServiceImpl.save(opportunityTypeDto);

        verify(this.opportunityTypeRepository).save(opportunityTypeEntity.capture());

        assertEquals(NOT_EXISTING_NAME, opportunityTypeEntity.getValue().getName());
        assertEquals(NOT_EXISTING_PRIORITY, opportunityTypeEntity.getValue().getPriority());
    }

    @Test
    public void editItemWithDupplicatedNameShouldThrowError() throws AlreadyExistsException, EntityNotFoundException {

        OpportunityTypeDto opportunityTypeDto = new OpportunityTypeDto();
        opportunityTypeDto.setName(EXISTS_NAME);
        opportunityTypeDto.setPriority(NOT_EXISTING_PRIORITY);

        OpportunityTypeEntity opportunityTypeEntity = mock(OpportunityTypeEntity.class);

        this.opportunityTypeServiceImpl.save(opportunityTypeDto);

        when(this.opportunityTypeRepository.getByName(EXISTS_NAME)).thenReturn(opportunityTypeEntity);

        assertThrows(AlreadyExistsException.class, () -> opportunityTypeServiceImpl.save(opportunityTypeDto));

        verify(this.opportunityTypeRepository, never()).save(opportunityTypeEntity);
    }

    @Test
    public void editItemWithDupplicatedPriorityShouldThrowError()
            throws AlreadyExistsException, EntityNotFoundException {

        OpportunityTypeDto opportunityTypeDto = new OpportunityTypeDto();
        opportunityTypeDto.setName(NOT_EXISTING_NAME);
        opportunityTypeDto.setPriority(EXISTS_PRIORITY);

        OpportunityTypeEntity opportunityTypeEntity = mock(OpportunityTypeEntity.class);

        this.opportunityTypeServiceImpl.save(opportunityTypeDto);

        when(this.opportunityTypeRepository.getByPriority(EXISTS_PRIORITY)).thenReturn(opportunityTypeEntity);

        assertThrows(AlreadyExistsException.class, () -> opportunityTypeServiceImpl.save(opportunityTypeDto));

        verify(this.opportunityTypeRepository, never()).save(opportunityTypeEntity);
    }
}
