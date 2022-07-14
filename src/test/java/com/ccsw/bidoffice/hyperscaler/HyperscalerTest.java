package com.ccsw.bidoffice.hyperscaler;

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

import com.ccsw.bidoffice.hyperscaler.HyperscalerRepository;
import com.ccsw.bidoffice.hyperscaler.HyperscalerServiceImpl;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

@ExtendWith(MockitoExtension.class)
public class HyperscalerTest {

    @Mock
    private HyperscalerRepository adminRepository;

    @InjectMocks
    private HyperscalerServiceImpl adminServiceImpl;

    @Test
    public void getAllFromHyperscaleShouldReturnAllDataFromHyperscaler() {

        List<HyperscalerEntity> data = new ArrayList<>();

        // Añadimos 4 datos en el Entity
        data.add(mock(HyperscalerEntity.class));
        data.add(mock(HyperscalerEntity.class));
        data.add(mock(HyperscalerEntity.class));
        data.add(mock(HyperscalerEntity.class));

        when(adminRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(data);

        List<HyperscalerEntity> list = adminServiceImpl.getAllDataFromHyperscaler();
        assertNotNull(list);
        assertEquals(4, list.size());

    }
}
