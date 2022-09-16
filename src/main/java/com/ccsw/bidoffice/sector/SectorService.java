package com.ccsw.bidoffice.sector;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.UpdateConflictException;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.ccsw.bidoffice.sector.model.SectorEntity;

public interface SectorService {

    /**
     * Devuelve todos los sectores de la base de datos en un listado, ordenados por
     * prioridad.
     * 
     * @return List conteniendo todos los sectores.
     */
    List<SectorEntity> findAllSectorOrderPriority();

    /**
     * Borra un sector de la base de datos.
     * 
     * @param id Long Identificador del sector a borrar.
     * @throws AlreadyExistsException Excepción lanzada si el sector está siendo
     *                                utilizado en alguna oferta.
     */
    void delete(Long id) throws AlreadyExistsException;

    /**
     * Recupera un sector buscando por identificador.
     * 
     * @param id Identificador del sector a buscar.
     * @return Objeto SectorEntity
     * @throws EntityNotFoundException Lanza una excepción si el sector no existe.
     */
    SectorEntity getById(Long id) throws EntityNotFoundException;

    /**
     * Guarda o modifica un sector existente en la base de datos.
     * 
     * @param sectorDto Objeto DTO del sector a guardar/modificar.
     * 
     * @throws EntityNotFoundException Si no encuentra el sector a modificar.
     * @throws AlreadyExistsException  Si el sector ya existe al guardar.
     * @throws UpdateConflictException Si la fecha de creación de oferta está fuera
     *                                 del rango.
     */
    void saveSector(SectorDto sectorDto)
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException;

}
