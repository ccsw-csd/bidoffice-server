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
    public ProjectTypeEntity modifyProjectType(ProjectTypeDto projectTypeDto) throws EntityNotFoundException {

        if(projectTypeDto.getId()==null){
            throw new EntityNotFoundException();
        }

        ProjectTypeEntity updateProjectType = this.get(projectTypeDto.getId());
        BeanUtils.copyProperties(projectTypeDto, updateProjectType, "id");
        return this.projectTypeRepository.save(updateProjectType);
    }

}
