package com.ccsw.bidoffice.technology;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

public interface TechnologyService {

    /**
     * Devuelve todas las tecnologías de la base de datos en un listado, ordenadas
     * por prioridad.
     */
    List<TechnologyEntity> findAllOrderByPriority();

    /**
     * Borra una tecnología de la base de datos.
     * 
     * @param id Long Identificador de la tecnología a borrar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si esta tecnología está
     *                                siendo utilizada en alguna oferta.
     */
    void delete(Long id) throws AlreadyExistsException;
}
