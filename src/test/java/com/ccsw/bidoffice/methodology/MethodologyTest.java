package com.ccsw.bidoffice.methodology;

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
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MethodologyTest {

    public static final Integer TOTAL_METHODOLOGY = 1;

    private static final Long EXISTING_ID = null;

    private static final Long NOT_EXISTING_ID = null;

    @Mock
    private OfferDataTechnologyServiceImpl offerData;

    @Mock
    private MethodologyRepository methodologyRepository;

    @InjectMocks
    private MethodologyServiceImpl methodologyServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<MethodologyEntity> list = new ArrayList<>();
        list.add(mock(MethodologyEntity.class));

        when(methodologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<MethodologyEntity> methodologys = methodologyServiceImpl.findAllMethodologyOrderPriority();

        assertNotNull(methodologys);
        assertEquals(TOTAL_METHODOLOGY, methodologys.size());

    }

    @Test
    public void deleteIfExistsInOfferShouldThrowError() {
        when(this.offerData.checkIfExistsByMethodologyId(EXISTING_ID)).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> methodologyServiceImpl.delete(EXISTING_ID));
        verify(this.methodologyRepository, never()).deleteById(EXISTING_ID);
    }

    @Test
    public void deleteIfDoesNotExistOfferShouldDelete() throws AlreadyExistsException {
        when(this.offerData.checkIfExistsByMethodologyId(NOT_EXISTING_ID)).thenReturn(false);
        this.methodologyServiceImpl.delete(NOT_EXISTING_ID);
        verify(this.methodologyRepository).deleteById(NOT_EXISTING_ID);
    }

}
