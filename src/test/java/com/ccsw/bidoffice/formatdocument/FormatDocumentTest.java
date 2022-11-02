package com.ccsw.bidoffice.formatdocument;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentDto;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentEntity;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FormatDocumentTest {

    public static final Integer TOTAL_DATA = 2;

    public static final Long ID_EXIST = 1L;

    public static final Long ID_NOT_EXIST = 0L;

    public static final String NAME_EXIST = "pdf";

    public static final Integer PRIORITY_EXIST = 1;

    public static final String NOT_EXISTING_NAME = "txt";

    public static final Integer NOT_EXISTING_PRIORITY = 3;

    @Mock
    private FormatDocumentRepository formatDocumentRepository;

    @Mock
    private OfferDataFileServiceImpl offerDataFileServiceImpl;

    @InjectMocks
    private FormatDocumentServiceImpl formatDocumentServiceImpl;

    private FormatDocumentDto formatDocumentDto;

    @BeforeEach
    private void setUp() {
        this.formatDocumentDto = new FormatDocumentDto();
    }

    @Test
    public void getAllDataShouldReturnFormatDocumentList() {

        List<FormatDocumentEntity> data = new ArrayList<>();

        data.add(mock(FormatDocumentEntity.class));
        data.add(mock(FormatDocumentEntity.class));

        when(formatDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(data);

        List<FormatDocumentEntity> list = formatDocumentServiceImpl.findAllFormatDocumentOrderPriority();
        assertNotNull(list);
        assertEquals(2, list.size());

    }

    @Test
    public void deleteExistsItemIdShouldDeleteFormatDocument() throws AlreadyExistsException {

        when(this.offerDataFileServiceImpl.checkExistsByFormatDocumentId(ID_EXIST)).thenReturn(false);

        this.formatDocumentServiceImpl.delete(ID_EXIST);

        verify(this.formatDocumentRepository).deleteById(ID_EXIST);
    }

    @Test
    public void deleteIfNotExistsItemIdShoulThrowException() throws AlreadyExistsException {
        when(this.offerDataFileServiceImpl.checkExistsByFormatDocumentId(ID_NOT_EXIST)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> formatDocumentServiceImpl.delete(ID_NOT_EXIST));

        verify(this.formatDocumentRepository, never()).deleteById(ID_EXIST);

    }

    @Test
    public void saveNewItemWhenAttributesDontMatchShouldSave() throws AlreadyExistsException, EntityNotFoundException {
        formatDocumentDto.setName(NOT_EXISTING_NAME);
        formatDocumentDto.setPriority(NOT_EXISTING_PRIORITY);

        ArgumentCaptor<FormatDocumentEntity> formatDocumentEntity = ArgumentCaptor.forClass(FormatDocumentEntity.class);

        this.formatDocumentServiceImpl.save(formatDocumentDto);

        verify(this.formatDocumentRepository).save(formatDocumentEntity.capture());

        assertEquals(NOT_EXISTING_NAME, formatDocumentEntity.getValue().getName());
        assertEquals(NOT_EXISTING_PRIORITY, formatDocumentEntity.getValue().getPriority());
    }

    @Test
    public void saveNewItemWhenNameMatchesShouldNotSave() throws AlreadyExistsException, EntityNotFoundException {
        formatDocumentDto.setName(NAME_EXIST);
        formatDocumentDto.setPriority(NOT_EXISTING_PRIORITY);

        FormatDocumentEntity formatDocumentEntity = mock(FormatDocumentEntity.class);

        this.formatDocumentServiceImpl.save(formatDocumentDto);

        when(this.formatDocumentRepository.findByNameIgnoreCaseContaining(NAME_EXIST)).thenReturn(formatDocumentEntity);

        assertThrows(AlreadyExistsException.class, () -> formatDocumentServiceImpl.save(formatDocumentDto));

        verify(this.formatDocumentRepository, never()).save(formatDocumentEntity);

    }

    @Test
    public void saveNewItemWhenPriorityMatchesShouldNotSave() throws AlreadyExistsException, EntityNotFoundException {

        formatDocumentDto.setName(NOT_EXISTING_NAME);
        formatDocumentDto.setPriority(PRIORITY_EXIST);

        FormatDocumentEntity formatDocumentEntity = mock(FormatDocumentEntity.class);

        this.formatDocumentServiceImpl.save(formatDocumentDto);

        when(this.formatDocumentRepository.findByPriority(PRIORITY_EXIST)).thenReturn(formatDocumentEntity);

        assertThrows(AlreadyExistsException.class, () -> formatDocumentServiceImpl.save(formatDocumentDto));

        verify(this.formatDocumentRepository, never()).save(formatDocumentEntity);

    }

    @Test
    public void editFormatDocumentWhenAttributesDontExistShouldEdit()
            throws AlreadyExistsException, EntityNotFoundException {

        formatDocumentDto.setId(ID_EXIST);
        formatDocumentDto.setName(NOT_EXISTING_NAME);
        formatDocumentDto.setPriority(NOT_EXISTING_PRIORITY);

        FormatDocumentEntity formatDocumentEntity = mock(FormatDocumentEntity.class);

        when(this.formatDocumentRepository.findByNameIgnoreCaseContaining(NOT_EXISTING_NAME)).thenReturn(null);

        when(this.formatDocumentRepository.findByPriority(NOT_EXISTING_PRIORITY)).thenReturn(null);

        when(this.formatDocumentRepository.findById(ID_EXIST)).thenReturn(Optional.of(formatDocumentEntity));

        this.formatDocumentServiceImpl.save(formatDocumentDto);

        verify(this.formatDocumentRepository).save(formatDocumentEntity);

    }
}
