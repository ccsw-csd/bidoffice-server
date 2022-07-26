package com.ccsw.bidoffice.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
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

import com.ccsw.bidoffice.filetype.FileTypeRepository;
import com.ccsw.bidoffice.filetype.FileTypeServiceImpl;
import com.ccsw.bidoffice.filetype.model.FileTypeEntity;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileRepository;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

@ExtendWith(MockitoExtension.class)
public class FileTypeTest {

    @Mock
    private FileTypeRepository fileTypeRepository;

    @Mock
    private OfferDataFileRepository offerDataFileRepository;

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

    @Test
    public void deleteExistsFileTypeIdShouldDelete() {

        fileTypeService.delete(EXISTS_FILETYPE_ID);

        verify(fileTypeRepository).deleteById(EXISTS_FILETYPE_ID);
    }

    @Test
    public void checkIfOfferWithExistingIdShouldReturnTrue() {

        List<OfferDataFileEntity> files = new ArrayList<>();
        files.add(mock(OfferDataFileEntity.class));
        files.add(mock(OfferDataFileEntity.class));
        files.add(mock(OfferDataFileEntity.class));
        files.add(mock(OfferDataFileEntity.class));

        when(this.offerDataFileRepository.findAllByFileTypeId(EXISTS_FILETYPE_ID)).thenReturn(files);

        boolean result = fileTypeService.checkIfOffersWithSameId(EXISTS_FILETYPE_ID);

        assertNotNull(result);
        assertEquals(true, result);
    }

    public static final Long NEW_FILETYPE_ID = 10L;

    @Test
    public void checkIfOfferWithNotExistingIdShouldReturnTrue() {

        List<OfferDataFileEntity> files = new ArrayList<>();

        when(this.offerDataFileRepository.findAllByFileTypeId(NEW_FILETYPE_ID)).thenReturn(files);

        boolean result = fileTypeService.checkIfOffersWithSameId(NEW_FILETYPE_ID);

        assertNotNull(result);
        assertEquals(false, result);
    }

}
