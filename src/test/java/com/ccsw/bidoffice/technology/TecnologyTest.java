package com.ccsw.bidoffice.technology;

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

import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@ExtendWith(MockitoExtension.class)
public class TecnologyTest {

    public static final Integer TOTAL_TECHNOLOGIES = 1;

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyServiceImpl technologyServiceImpl;

    /**
     * Comprueba que se devuelve un listado ordenado por prioridades.
     * 
     * El test debe devolver un listado ordenado por el atributo "priority", de
     * forma ascendente.
     */
    @Test
    public void findAllShouldReturlListFileOrderedByPriority() {

        List<TechnologyEntity> list = new ArrayList<>();
        list.add(mock(TechnologyEntity.class));

        when(technologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<TechnologyEntity> technologies = technologyServiceImpl.findAllOrderByPriority();

        assertNotNull(technologies);
        assertEquals(TOTAL_TECHNOLOGIES, technologies.size());

    }

}
