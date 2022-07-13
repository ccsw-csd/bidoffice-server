package com.ccsw.bidoffice.projecttype;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

public interface ProjectTypeRepository extends CrudRepository<ProjectTypeEntity, Long> {

    List<ProjectTypeEntity> findAll(Sort sort);
}
