package com.ccsw.bidoffice.projecttype;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offerdataproject.OfferDataProjectService;
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

}
