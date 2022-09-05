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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offer.OfferServiceImpl;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@ExtendWith(MockitoExtension.class)
public class SectorTest {

    // Constantes.
    private static final Integer TOTAL_SECTOR = 1;

    private static final Long EXISTS_SECTOR_ID = 1L;
    private static final Long NOT_EXISTS_SECTOR_ID = 0L;

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

        when(this.offerServiceImpl.checkIfExistsOffer(EXISTS_SECTOR_ID)).thenReturn(false);
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

        when(this.offerServiceImpl.checkIfExistsOffer(NOT_EXISTS_SECTOR_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> sectorServiceImpl.delete(NOT_EXISTS_SECTOR_ID));

        verify(this.sectorRepository, never()).deleteById(NOT_EXISTS_SECTOR_ID);

    }
}
