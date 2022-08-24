package com.ccsw.bidoffice.sector;

import java.util.List;

import com.ccsw.bidoffice.sector.model.SectorEntity;

public interface SectorService {

    /**
     * Devuelve todos los sectores de la base de datos en un listado, ordenados por
     * prioridad.
     * 
     * @return List conteniendo todos los sectores.
     */
    List<SectorEntity> findAllSectorOrderPriority();
}
