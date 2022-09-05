package com.ccsw.bidoffice.sector;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
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
}
