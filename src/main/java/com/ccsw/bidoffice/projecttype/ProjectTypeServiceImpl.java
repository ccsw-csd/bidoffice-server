package com.ccsw.bidoffice.projecttype;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offerdataproject.OfferDataProjectService;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Autowired
    private OfferDataProjectService offerDataProjectService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectTypeEntity get(Long id) throws EntityNotFoundException {

        return projectTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectTypeEntity> findAllProjectTypeOrderPriority() {

        return this.projectTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws AlreadyExistsException {
        if(this.offerDataProjectService.checkExistsByProjectTypeId(id))
            throw new AlreadyExistsException();

        this.projectTypeRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectTypeEntity saveProjectType(ProjectTypeDto projectTypeDto) throws AlreadyExistsException, EntityNotFoundException {

        this.checkWhenProjectTypeAttributesAlreadyUsed(projectTypeDto);

        ProjectTypeEntity projectTypeEntity = null;

        if(projectTypeDto.getId()!=null){
            projectTypeEntity = get(projectTypeDto.getId());
        } else {
            projectTypeEntity = new ProjectTypeEntity();
        }

        BeanUtils.copyProperties(projectTypeDto, projectTypeEntity, "id");

        return this.projectTypeRepository.save(projectTypeEntity);
    }

    private void checkWhenProjectTypeAttributesAlreadyUsed(ProjectTypeDto dto) throws AlreadyExistsException {
        boolean nameExists = false;
        boolean priorityExists = false;

        if(dto.getId() == null){
            nameExists = this.projectTypeRepository.existsByName(dto.getName());
            priorityExists = this.projectTypeRepository.existsByPriority(dto.getPriority());
        } else {
            nameExists = this.projectTypeRepository.existsByIdIsNotAndName(dto.getId(), dto.getName());
            priorityExists = this.projectTypeRepository.existsByIdIsNotAndPriority(dto.getId(), dto.getPriority());
        }

        if(nameExists || priorityExists)
            throw new AlreadyExistsException();
    }

}
