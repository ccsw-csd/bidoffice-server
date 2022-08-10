package com.ccsw.bidoffice.filetype;

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
import com.ccsw.bidoffice.filetype.model.FileTypeDto;
import com.ccsw.bidoffice.filetype.model.FileTypeEntity;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FileTypeTest {

    @Mock
    private FileTypeRepository fileTypeRepository;

    @Mock
    private OfferDataFileServiceImpl offerDataFileServiceImpl;

    @InjectMocks
    private FileTypeServiceImpl fileTypeService;

    public static final Long EXISTS_FILETYPE_ID = 3L;
    public static final Long NEW_FILETYPE_ID = 10L;
    public static final String EXISTS_FILETYPE_NAME = "user1";
    public static final String ANOTHER_EXISTS_FILETYPE_NAME = "user2";
    public static final String NOT_EXISTS_FILETYPE_NAME = "New User";
    public static final Long EXISTS_FILETYPE_PRIORITY = 2L;
    public static final Long NOT_EXISTS_FILETYPE_PRIORITY = 23L;

    @Test
    public void findAllFileTypes() {

        List<FileTypeEntity> files = new ArrayList<>();
        files.add(mock(FileTypeEntity.class));
        files.add(mock(FileTypeEntity.class));
        files.add(mock(FileTypeEntity.class));
        files.add(mock(FileTypeEntity.class));

        when(fileTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(files);

        List<FileTypeEntity> filetypes = fileTypeService.getAllFromFileType();

        assertNotNull(filetypes);
        assertEquals(4, filetypes.size());

    }

    @Test
    public void deleteExistsFileTypeIdShouldDelete() throws AlreadyExistsException {
        when(this.offerDataFileServiceImpl.checkExistsByFileTypeId(NEW_FILETYPE_ID)).thenReturn(false);

        fileTypeService.delete(NEW_FILETYPE_ID);

        verify(fileTypeRepository).deleteById(NEW_FILETYPE_ID);
    }

    @Test
    public void deleteFileTypeWithExistingShouldThrowException() {

        when(this.offerDataFileServiceImpl.checkExistsByFileTypeId(EXISTS_FILETYPE_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> this.fileTypeService.delete(EXISTS_FILETYPE_ID));
        verify(this.fileTypeRepository, never()).deleteById(EXISTS_FILETYPE_ID);
    }

    @Test
    public void saveNewFileTypeButWrongNameShouldThrowException() {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setName(EXISTS_FILETYPE_NAME);
        fileTypeDto.setPriority(4L);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);

        when(this.fileTypeRepository.existsByName(EXISTS_FILETYPE_NAME)).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> this.fileTypeService.save(fileTypeDto));
        verify(this.fileTypeRepository, never()).save(fileTypeEntity);
    }

    @Test
    public void saveNewFileTypeButWrongPriorityShouldThrowException() {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setName("NEW NAME");
        fileTypeDto.setPriority(EXISTS_FILETYPE_PRIORITY);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);

        when(this.fileTypeRepository.existsByPriority(EXISTS_FILETYPE_PRIORITY)).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> this.fileTypeService.save(fileTypeDto));
        verify(this.fileTypeRepository, never()).save(fileTypeEntity);
    }

    @Test
    public void saveFileTypeShouldSave() throws AlreadyExistsException {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setName("NEW");
        fileTypeDto.setPriority(15L);

        ArgumentCaptor<FileTypeEntity> fileTypeEntity = ArgumentCaptor.forClass(FileTypeEntity.class);

        when(this.fileTypeRepository.existsByName("NEW")).thenReturn(false);
        when(this.fileTypeRepository.existsByPriority(15L)).thenReturn(false);

        fileTypeService.save(fileTypeDto);

        verify(this.fileTypeRepository).save(fileTypeEntity.capture());
    }

    @Test
    public void modifyFileTypeButWrongPriorityShouldThrowException() {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setId(EXISTS_FILETYPE_ID);
        fileTypeDto.setName(EXISTS_FILETYPE_NAME);
        fileTypeDto.setPriority(EXISTS_FILETYPE_PRIORITY);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);

        when(fileTypeRepository.existsByPriorityAndIdIsNot(EXISTS_FILETYPE_PRIORITY, EXISTS_FILETYPE_ID))
                .thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> this.fileTypeService.save(fileTypeDto));

        verify(this.fileTypeRepository, never()).save(fileTypeEntity);
    }

    @Test
    public void modifyFileTypeButWrongNameShouldThrowException() {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setId(EXISTS_FILETYPE_ID);
        fileTypeDto.setName(ANOTHER_EXISTS_FILETYPE_NAME);
        fileTypeDto.setPriority(EXISTS_FILETYPE_PRIORITY);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);
        when(fileTypeRepository.existsByNameAndIdIsNot(ANOTHER_EXISTS_FILETYPE_NAME, EXISTS_FILETYPE_ID))
                .thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> this.fileTypeService.save(fileTypeDto));

        verify(this.fileTypeRepository, never()).save(fileTypeEntity);
    }

    @Test
    public void modifyOnlyNameFileTypeShouldModify() throws AlreadyExistsException {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setId(EXISTS_FILETYPE_ID);
        fileTypeDto.setName(NOT_EXISTS_FILETYPE_NAME);
        fileTypeDto.setPriority(EXISTS_FILETYPE_PRIORITY);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);

        when(this.fileTypeRepository.findById(EXISTS_FILETYPE_ID)).thenReturn(Optional.of(fileTypeEntity));

        when(fileTypeRepository.existsByNameAndIdIsNot(NOT_EXISTS_FILETYPE_NAME, EXISTS_FILETYPE_ID)).thenReturn(false);
        this.fileTypeService.save(fileTypeDto);

        verify(this.fileTypeRepository).save(fileTypeEntity);
    }

    @Test
    public void modifyOnlyPriorityFileTypeShouldModify() throws AlreadyExistsException {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setId(EXISTS_FILETYPE_ID);
        fileTypeDto.setName(EXISTS_FILETYPE_NAME);
        fileTypeDto.setPriority(NOT_EXISTS_FILETYPE_PRIORITY);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);
        when(this.fileTypeRepository.findById(EXISTS_FILETYPE_ID)).thenReturn(Optional.of(fileTypeEntity));

        when(fileTypeRepository.existsByPriorityAndIdIsNot(NOT_EXISTS_FILETYPE_PRIORITY, EXISTS_FILETYPE_ID))
                .thenReturn(false);
        this.fileTypeService.save(fileTypeDto);

        verify(this.fileTypeRepository).save(fileTypeEntity);
    }

    @Test
    public void modifyFileTypeShouldModify() throws AlreadyExistsException {

        FileTypeDto fileTypeDto = new FileTypeDto();

        fileTypeDto.setId(EXISTS_FILETYPE_ID);
        fileTypeDto.setName(NOT_EXISTS_FILETYPE_NAME);
        fileTypeDto.setPriority(NOT_EXISTS_FILETYPE_PRIORITY);

        FileTypeEntity fileTypeEntity = mock(FileTypeEntity.class);

        when(this.fileTypeRepository.findById(EXISTS_FILETYPE_ID)).thenReturn(Optional.of(fileTypeEntity));

        this.fileTypeService.save(fileTypeDto);

        verify(this.fileTypeRepository).save(fileTypeEntity);
    }
}