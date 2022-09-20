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
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offertechnology.OfferTechnologyServiceImpl;
import com.ccsw.bidoffice.technology.model.TechnologyDto;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@ExtendWith(MockitoExtension.class)
public class TecnologyTest {

    // Constantes.
    private static final Integer TOTAL_TECHNOLOGIES = 3;

    private static final Long EXISTS_TECHNOLOGY_ID = 1L;
    private static final Long NOT_EXISTS_TECHNOLOGY_ID = 0L;

    private static final String NOT_EXISTING_NAME = "Not Exists Test";
    private static final int NOT_EXISTING_PRIORITY = 100;

    private static final String EXISTS_NAME = "admin";
    private static final int EXISTS_PRIORITY = 1;

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

    /**
     * Intenta borrar una tecnología que no existe en la base de datos. El test debe
     * verificar que no se ha borrado la tecnología.
     * 
     * verify (repositorio, never())... verifica que la interacción propuesta no se
     * ha producido.
     * 
     * @throws AlreadyExistsException
     */
    @Test
    public void deleteIfNotExistsTechnologyIdShouldThrowsException() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkExistsByTechnologyId(NOT_EXISTS_TECHNOLOGY_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> technologyServiceImpl.delete(NOT_EXISTS_TECHNOLOGY_ID));

        verify(this.technologyRepository, never()).deleteById(NOT_EXISTS_TECHNOLOGY_ID);

    }

    /**
     * Guarda una nueva tecnología, sin existir previamente nombre ni prioridad.
     * 
     * El test debe devolver que la tecnología propuesta se ha guardado
     * correctamente en la base de datos.
     * 
     * @throws AlreadyExistsException
     * @throws EntityNotFoundException
     */
    @Test
    public void saveNewTechnologyWhenIdAndNameDoesntExistsShouldSave()
            throws AlreadyExistsException, EntityNotFoundException {

        TechnologyDto technologyDto = new TechnologyDto();
        technologyDto.setName(NOT_EXISTING_NAME);
        technologyDto.setPriority(NOT_EXISTING_PRIORITY);

        ArgumentCaptor<TechnologyEntity> technologyEntity = ArgumentCaptor.forClass(TechnologyEntity.class);

        this.technologyServiceImpl.saveTechnology(technologyDto);

        verify(this.technologyRepository).save(technologyEntity.capture());

        assertEquals(NOT_EXISTING_NAME, technologyEntity.getValue().getName());
        assertEquals(NOT_EXISTING_PRIORITY, technologyEntity.getValue().getPriority());

    }

    /**
     * Guarda una nueva tecnología, aunque utilizando un nombre que ya existe en la
     * base de datos.
     * 
     * El test debe devolver que la tecnología no ha sido guardada en la base de
     * datos.
     * 
     * @throws AlreadyExistsException  Excepción lanzada cuando la tecnología ya
     *                                 existe, por nombre o prioridad.
     * @throws EntityNotFoundException
     */
    @Test
    public void saveNewTechnologyWhenNameExistsShouldNotSave() throws AlreadyExistsException, EntityNotFoundException {

        TechnologyDto technologyDto = new TechnologyDto();
        technologyDto.setName(EXISTS_NAME);
        technologyDto.setPriority(NOT_EXISTING_PRIORITY);

        TechnologyEntity technologyEntity = mock(TechnologyEntity.class);

        this.technologyServiceImpl.saveTechnology(technologyDto);

        when(this.technologyRepository.getByName(EXISTS_NAME)).thenReturn(technologyEntity);

        assertThrows(AlreadyExistsException.class, () -> technologyServiceImpl.saveTechnology(technologyDto));

        verify(this.technologyRepository, never()).save(technologyEntity);
    }

    /**
     * Guarda una nueva tecnología, aunque utilizando una prioridad que ya existe en
     * la base de datos.
     * 
     * El test debe devolver que la tecnología no ha sido guardada en la base de
     * datos.
     * 
     * @throws AlreadyExistsException  Excepción lanzada cuando la tecnología ya
     *                                 existe, por nombre o prioridad.
     * @throws EntityNotFoundException
     */
    @Test
    public void saveNewTechnologyWhenPriorityExistsShouldNotSave()
            throws AlreadyExistsException, EntityNotFoundException {

        TechnologyDto technologyDto = new TechnologyDto();
        technologyDto.setName(NOT_EXISTING_NAME);
        technologyDto.setPriority(EXISTS_PRIORITY);

        TechnologyEntity technologyEntity = mock(TechnologyEntity.class);

        this.technologyServiceImpl.saveTechnology(technologyDto);

        when(this.technologyRepository.getByPriority(EXISTS_PRIORITY)).thenReturn(technologyEntity);

        assertThrows(AlreadyExistsException.class, () -> technologyServiceImpl.saveTechnology(technologyDto));

        verify(this.technologyRepository, never()).save(technologyEntity);

    }

    /**
     * Modifica una tecnología cuando el identificador existe, pero el nombre y su
     * prioridad no.
     * 
     * El test debe modificar la tecnología y verificar que se ha guardado.
     * 
     */
    @Test
    public void editTechnologyWhenNameAndPriorityDoesntExistsShouldEdit()
            throws AlreadyExistsException, EntityNotFoundException {

        TechnologyDto technologyDto = new TechnologyDto();
        technologyDto.setId(EXISTS_TECHNOLOGY_ID);
        technologyDto.setName(NOT_EXISTING_NAME);
        technologyDto.setPriority(NOT_EXISTING_PRIORITY);

        TechnologyEntity technologyEntity = mock(TechnologyEntity.class);

        when(this.technologyRepository.getByName(NOT_EXISTING_NAME)).thenReturn(null);

        when(this.technologyRepository.getByPriority(NOT_EXISTING_PRIORITY)).thenReturn(null);

        when(this.technologyRepository.findById(EXISTS_TECHNOLOGY_ID)).thenReturn(Optional.of(technologyEntity));

        this.technologyServiceImpl.saveTechnology(technologyDto);

        verify(this.technologyRepository).save(technologyEntity);
    }
}
