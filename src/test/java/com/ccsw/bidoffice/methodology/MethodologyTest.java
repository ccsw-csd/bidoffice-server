package com.ccsw.bidoffice.methodology;

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

import com.ccsw.bidoffice.methodology.model.MethodologyEntity;

@ExtendWith(MockitoExtension.class)
public class MethodologyTest {

    public static final Integer TOTAL_METHODOLOGY = 1;

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
}
