package com.ccsw.bidoffice.methodology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;

@ExtendWith(MockitoExtension.class)
public class MethodologyTest {

    public static final Integer TOTAL_METHODOLOGY = 1;
    public static final Long EXISTS_ID = 1L;
    public static final String NEW_NAME = "New name";
    public static final String EXISTS_NAME = "Otros4";
    public static final Integer EXISTS_PRIORITY = 1;

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
    public void saveExistsMethodologyShouldUpdate() throws AlreadyExistsException {
        MethodologyDto methodologyDto = new MethodologyDto();
        methodologyDto.setId(EXISTS_ID);
        methodologyDto.setName(NEW_NAME);

        MethodologyEntity methodology = mock(MethodologyEntity.class);
        when(methodologyRepository.findById(EXISTS_ID)).thenReturn(Optional.of(methodology));

        this.methodologyServiceImpl.save(EXISTS_ID, methodologyDto);

        verify(methodologyRepository).save(methodology);
    }

    @Test
    public void saveExistsMethodologyAndNameShouldThrowException() throws AlreadyExistsException {
        MethodologyDto methodologyDto = new MethodologyDto();
        methodologyDto.setId(EXISTS_ID);
        methodologyDto.setName(EXISTS_NAME);

        MethodologyEntity methodology = mock(MethodologyEntity.class);
        when(methodologyRepository.existsByNameAndIdIsNot(EXISTS_NAME, EXISTS_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> methodologyServiceImpl.save(EXISTS_ID, methodologyDto));

        verify(methodologyRepository, never()).save(methodology);
    }

    @Test
    public void saveExistsMethodologyAndPriorityShouldThrowException() throws AlreadyExistsException {
        MethodologyDto methodologyDto = new MethodologyDto();
        methodologyDto.setId(EXISTS_ID);
        methodologyDto.setPriority(EXISTS_PRIORITY);

        MethodologyEntity methodology = mock(MethodologyEntity.class);
        when(methodologyRepository.existsByPriorityAndIdIsNot(EXISTS_PRIORITY, EXISTS_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> methodologyServiceImpl.save(EXISTS_ID, methodologyDto));

        verify(methodologyRepository, never()).save(methodology);
    }

    @Test
    public void saveExistsMethodologyAndNameAndPriorityShouldThrowException() throws AlreadyExistsException {
        MethodologyDto methodologyDto = new MethodologyDto();
        methodologyDto.setId(EXISTS_ID);
        methodologyDto.setName(EXISTS_NAME);
        methodologyDto.setPriority(EXISTS_PRIORITY);

        MethodologyEntity methodology = mock(MethodologyEntity.class);
        when(methodologyRepository.existsByNameAndIdIsNot(EXISTS_NAME, EXISTS_ID)).thenReturn(true);
        when(methodologyRepository.existsByPriorityAndIdIsNot(EXISTS_PRIORITY, EXISTS_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> methodologyServiceImpl.save(EXISTS_ID, methodologyDto));

        verify(methodologyRepository, never()).save(methodology);
    }
}
