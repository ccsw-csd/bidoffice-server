package com.ccsw.bidoffice.sector;

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
import com.ccsw.bidoffice.common.exception.UpdateConflictException;
import com.ccsw.bidoffice.offer.OfferServiceImpl;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@ExtendWith(MockitoExtension.class)
public class SectorTest {

    // Constantes.
    private static final Integer TOTAL_SECTOR = 1;

    private static final Long EXISTS_SECTOR_ID = 1L;
    private static final Long NOT_EXISTS_SECTOR_ID = 0L;

    private static final String NOT_EXISTING_NAME = "TOP GUN";
    private static final int NOT_EXISTING_PRIORITY = 30;

    private static final String EXISTS_NAME = "Otros";
    private static final int EXISTS_PRIORITY = 1;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private OfferServiceImpl offerServiceImpl;

    @InjectMocks
    private SectorServiceImpl sectorServiceImpl;

    /**
     * Comprueba que se devuelve un listado ordenado por prioridades.
     * 
     * El test debe devolver un listado ordenado por el atributo "priority", de
     * forma ascendente.
     */
    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<SectorEntity> list = new ArrayList<>();
        list.add(mock(SectorEntity.class));

        when(sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<SectorEntity> sectors = sectorServiceImpl.findAllSectorOrderPriority();

        assertNotNull(sectors);
        assertEquals(TOTAL_SECTOR, sectors.size());

    }

    /**
     * Intenta borrar un sector existente.
     * 
     * @throws AlreadyExistsException Lanza la excepción si se estuviera utilizando
     *                                el sector en alguna oferta, aunque esto se
     *                                contempla en el test de integración.
     */
    @Test
    public void deleteExistsSectorShouldDelete() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkIfSectorIsUsingInOfferBySectorId(EXISTS_SECTOR_ID)).thenReturn(false);
        this.sectorServiceImpl.delete(EXISTS_SECTOR_ID);

        verify(this.sectorRepository).deleteById(EXISTS_SECTOR_ID);
    }

    /**
     * Intenta borrar un sector que no existe en la base de datos. El test debe
     * verificar que no se ha borrado el sector.
     * 
     * @throws AlreadyExistsException
     */
    @Test
    public void deleteIfNotExistsSectorIdShouldThrowsException() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkIfSectorIsUsingInOfferBySectorId(NOT_EXISTS_SECTOR_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> sectorServiceImpl.delete(NOT_EXISTS_SECTOR_ID));

        verify(this.sectorRepository, never()).deleteById(NOT_EXISTS_SECTOR_ID);

    }

    /**
     * Guarda un nuevo sector, sin existir previamente nombre ni prioridad.
     * 
     * El test debe devolver que el sector propuesto se ha guardado correctamente en
     * la base de datos.
     * 
     * @throws AlreadyExistsException
     * @throws EntityNotFoundException
     * @throws UpdateConflictException
     */
    @Test
    public void saveNewSectorWhenIdAndNameDoesntExistsShouldSave()
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException {

        SectorDto sectorDto = new SectorDto();
        sectorDto.setName(NOT_EXISTING_NAME);
        sectorDto.setPriority(NOT_EXISTING_PRIORITY);

        ArgumentCaptor<SectorEntity> sectorEntity = ArgumentCaptor.forClass(SectorEntity.class);

        this.sectorServiceImpl.saveSector(sectorDto);

        verify(this.sectorRepository).save(sectorEntity.capture());

        assertEquals(NOT_EXISTING_NAME, sectorEntity.getValue().getName());
        assertEquals(NOT_EXISTING_PRIORITY, sectorEntity.getValue().getPriority());

    }

    /**
     * Guarda un nuevo sector aunque utilizando un nombre que ya existe en la base
     * de datos.
     * 
     * El test debe devolver que el sector no ha sido guardado en la base de datos.
     * 
     * @throws AlreadyExistsException  Excepción lanzada cuando el sector ya existe,
     *                                 por nombre o prioridad.
     * @throws EntityNotFoundException
     * @throws UpdateConflictException
     */
    @Test
    public void saveNewSectorWhenNameExistsShouldNotSave()
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException {

        SectorDto sectorDto = new SectorDto();
        sectorDto.setName(EXISTS_NAME);
        sectorDto.setPriority(NOT_EXISTING_PRIORITY);

        SectorEntity sectorEntity = mock(SectorEntity.class);

        this.sectorServiceImpl.saveSector(sectorDto);

        when(this.sectorRepository.getByName(EXISTS_NAME)).thenReturn(sectorEntity);

        assertThrows(UpdateConflictException.class, () -> sectorServiceImpl.saveSector(sectorDto));

        verify(this.sectorRepository, never()).save(sectorEntity);
    }

    /**
     * Guarda un nuevo sector aunque utilizando una prioridad que ya existe en la
     * base de datos.
     * 
     * El test debe devolver que el sector no ha sido guardado en la base de datos.
     * 
     * @throws AlreadyExistsException  Excepción lanzada cuando el sector ya existe,
     *                                 por nombre o prioridad.
     * @throws EntityNotFoundException
     * @throws UpdateConflictException
     */
    @Test
    public void saveNewSectorWhenPriorityExistsShouldNotSave()
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException {

        SectorDto sectorDto = new SectorDto();
        sectorDto.setName(NOT_EXISTING_NAME);
        sectorDto.setPriority(EXISTS_PRIORITY);

        SectorEntity sectorEntity = mock(SectorEntity.class);

        this.sectorServiceImpl.saveSector(sectorDto);

        when(this.sectorRepository.getByPriority(EXISTS_PRIORITY)).thenReturn(sectorEntity);

        assertThrows(UpdateConflictException.class, () -> sectorServiceImpl.saveSector(sectorDto));

        verify(this.sectorRepository, never()).save(sectorEntity);
    }

    /**
     * Intenta guardar un nuevo sector cuando el nombre y la prioridad son los
     * mismos.
     * 
     * El test no debe guardar el sector y debe devolver una excepción.
     * 
     * @throws AlreadyExistsException Cuando existe exactamente esa tecnología con
     *                                la misma prioridad.
     */
    @Test
    public void editSectorWhenPriorityAndNameAlreadyExists() throws AlreadyExistsException, UpdateConflictException {

        SectorDto sectorDto = new SectorDto();
        sectorDto.setId(EXISTS_SECTOR_ID);
        sectorDto.setName(EXISTS_NAME);
        sectorDto.setPriority(EXISTS_PRIORITY);

        SectorEntity sectorEntity = mock(SectorEntity.class);

        when(this.sectorRepository.getByName(EXISTS_NAME)).thenReturn(sectorEntity);

        assertThrows(UpdateConflictException.class, () -> sectorServiceImpl.saveSector(sectorDto));

        verify(this.sectorRepository, never()).save(sectorEntity);
    }

    /**
     * Modifica un sector cuando el identificador existe, pero el nombre y su
     * prioridad no.
     * 
     * El test debe modificar el sector y verificar que se ha guardado.
     * 
     * @throws UpdateConflictException
     * 
     */
    @Test
    public void editTechnologyWhenNameAndPriorityDoesntExistsShouldEdit()
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException {

        SectorDto sectorDto = new SectorDto();
        sectorDto.setId(EXISTS_SECTOR_ID);
        sectorDto.setName(NOT_EXISTING_NAME);
        sectorDto.setPriority(NOT_EXISTING_PRIORITY);

        SectorEntity sectorEntity = mock(SectorEntity.class);

        when(this.sectorRepository.getByName(NOT_EXISTING_NAME)).thenReturn(null);

        when(this.sectorRepository.getByPriority(NOT_EXISTING_PRIORITY)).thenReturn(null);

        when(this.sectorRepository.findById(EXISTS_SECTOR_ID)).thenReturn(Optional.of(sectorEntity));

        this.sectorServiceImpl.saveSector(sectorDto);

        verify(this.sectorRepository).save(sectorEntity);
    }

}
