package com.ccsw.bidoffice.project_type;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.project_type.model.ProjectTypeEntity;

public interface ProjectTypeRepository extends CrudRepository<ProjectTypeEntity, Long> {

    List<ProjectTypeEntity> findAllByOrderByPriorityAsc();
}
