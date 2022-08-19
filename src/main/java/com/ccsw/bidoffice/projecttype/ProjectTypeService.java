package com.ccsw.bidoffice.projecttype;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

public interface ProjectTypeService {

    List<ProjectTypeEntity> findAllProjectTypeOrderPriority();

    /**
     * Borra un tipo de proyecto
     *
     * @param id
     *
     * @throws AlreadyExistsException
     */
    void delete(Long id) throws AlreadyExistsException;

}
