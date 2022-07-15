package com.ccsw.bidoffice.projecttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Override
    public List<ProjectTypeEntity> findAllProjectTypeOrderPriority() {

        return this.projectTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

}
