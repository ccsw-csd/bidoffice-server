package com.ccsw.bidoffice.technology;

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
import com.ccsw.bidoffice.offertechnology.OfferTechnologyServiceImpl;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@ExtendWith(MockitoExtension.class)
public class TecnologyTest {

    // Constantes.
    private static final Integer TOTAL_TECHNOLOGIES = 3;
    private static final Long EXISTS_TECHNOLOGY_ID = 1L;
    private static final Long NOT_EXISTS_TECHNOLOGY_ID = 0L;

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private OfferTechnologyServiceImpl offerServiceImpl;

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
        list.add(mock(TechnologyEntity.class));
        list.add(mock(TechnologyEntity.class));

        when(technologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<TechnologyEntity> technologies = technologyServiceImpl.findAllOrderByPriority();

        assertNotNull(technologies);
        assertEquals(TOTAL_TECHNOLOGIES, technologies.size());

    }

    /**
     * Intenta borrar una tecnología existente.
     * 
     * @throws AlreadyExistsException Lanza la excepción si se estuviera utilizando
     *                                la tecnología en alguna oferta, aunque esto se
     *                                contempla en el test de integración.
     */
    @Test
    public void deleteExistsTechnologyShouldDelete() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkExistsByTechnologyId(EXISTS_TECHNOLOGY_ID)).thenReturn(false);
        this.technologyServiceImpl.delete(EXISTS_TECHNOLOGY_ID);

        verify(this.technologyRepository).deleteById(EXISTS_TECHNOLOGY_ID);

    }

    @Test
    public void deleteIfNotExistsTechnologyIdShouldThrowsException() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkExistsByTechnologyId(NOT_EXISTS_TECHNOLOGY_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> technologyServiceImpl.delete(NOT_EXISTS_TECHNOLOGY_ID));

        verify(this.technologyRepository, never()).deleteById(NOT_EXISTS_TECHNOLOGY_ID);

    }

}
