package com.ccsw.bidoffice.sector;

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

import com.ccsw.bidoffice.sector.model.SectorEntity;

@ExtendWith(MockitoExtension.class)
public class SectorTest {

    public static final Integer TOTAL_SECTOR = 1;

    @Mock
    private SectorRepository sectorRepository;

    @InjectMocks
    private SectorServiceImpl sectorServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<SectorEntity> list = new ArrayList<>();
        list.add(mock(SectorEntity.class));

        when(sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<SectorEntity> sectors = sectorServiceImpl.findAllSectorOrderPriority();

        assertNotNull(sectors);
        assertEquals(TOTAL_SECTOR, sectors.size());

    }
}
