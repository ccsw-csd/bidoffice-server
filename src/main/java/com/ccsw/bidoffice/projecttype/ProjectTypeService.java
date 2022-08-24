package com.ccsw.bidoffice.projecttype;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

public interface ProjectTypeService {

    List<ProjectTypeEntity> findAllProjectTypeOrderPriority();

    /**
     * Recupera un tipo de proyecto por su id
     *
     * @param id
     * @return
     * @throws Exception
     */
    ProjectTypeEntity get(Long id) throws EntityNotFoundException;

    /**
     * Borra un tipo de proyecto
     *
     * @param id
     *
     * @throws AlreadyExistsException
     */
    void delete(Long id) throws AlreadyExistsException;

    /**
     * Modifica un tipo de proyecto
     *
     * @param dto
     */
    ProjectTypeEntity saveProjectType(ProjectTypeDto dto) throws AlreadyExistsException, EntityNotFoundException;

}
