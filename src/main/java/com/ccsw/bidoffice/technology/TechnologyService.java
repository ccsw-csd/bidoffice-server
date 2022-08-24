package com.ccsw.bidoffice.technology;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.technology.model.TechnologyDto;
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

    /**
     * Recupera una tecnología buscando por identificador.
     * 
     * @param id Identificador de la tecnología a buscar.
     * @return Objeto TechnologyEntity
     * @throws EntityNotFoundException Lanza una excepción si la tecnología no
     *                                 existe.
     */
    TechnologyEntity getById(Long id) throws EntityNotFoundException;

    /**
     * Registra una nueva tecnología
     * 
     * @param technologyDto Objeto DTO de la tecnología a guardar o modificar.
     * 
     * @throws AlreadyExistsException  Excepción si existe la tecnología.
     * @throws EntityNotFoundException Excepción si no encuentra el Entity.
     */
    void saveTechnology(TechnologyDto technologyDto) throws AlreadyExistsException, EntityNotFoundException;

}
