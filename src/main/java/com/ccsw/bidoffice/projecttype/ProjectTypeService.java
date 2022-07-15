package com.ccsw.bidoffice.projecttype;

import java.util.List;

import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

public interface ProjectTypeService {

    List<ProjectTypeEntity> findAllProjectTypeOrderPriority();
}
