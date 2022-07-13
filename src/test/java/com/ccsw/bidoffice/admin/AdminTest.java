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

import com.ccsw.bidoffice.admin.model.FileTypeEntity;

@ExtendWith(MockitoExtension.class)
public class AdminTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void findAllFileTypes() {

        List<FileTypeEntity> files = new ArrayList<>();
        files.add(mock(FileTypeEntity.class));
        files.add(mock(FileTypeEntity.class));
        files.add(mock(FileTypeEntity.class));
        files.add(mock(FileTypeEntity.class));

        when(adminRepository.findAllFileTypes()).thenReturn(files);

        List<FileTypeEntity> filetypes = adminService.getAllFromFileType();

        assertNotNull(filetypes);
        assertEquals(4, filetypes.size());

    }

}
