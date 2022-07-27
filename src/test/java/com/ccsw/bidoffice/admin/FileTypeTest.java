package com.ccsw.bidoffice.admin;

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
import com.ccsw.bidoffice.filetype.FileTypeRepository;
import com.ccsw.bidoffice.filetype.FileTypeServiceImpl;
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

    public static final Long EXISTS_FILETYPE_ID = 1L;
    public static final Long NEW_FILETYPE_ID = 10L;

    @Test
    public void deleteExistsFileTypeIdShouldDelete() throws AlreadyExistsException {
        when(this.offerDataFileServiceImpl.checkExistsById(NEW_FILETYPE_ID)).thenReturn(false);

        fileTypeService.delete(NEW_FILETYPE_ID);

        verify(fileTypeRepository).deleteById(NEW_FILETYPE_ID);
    }

    @Test
    public void deleteFileTypeWithExistingShouldThrowException() {

        when(this.offerDataFileServiceImpl.checkExistsById(EXISTS_FILETYPE_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> this.fileTypeService.delete(EXISTS_FILETYPE_ID));
        verify(this.fileTypeRepository, never()).deleteById(EXISTS_FILETYPE_ID);

    }

}
