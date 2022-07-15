package com.ccsw.bidoffice.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
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

@ExtendWith(MockitoExtension.class)
public class FileTypeTest {

    @Mock
    private FileTypeRepository fileTypeRepository;

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

}
