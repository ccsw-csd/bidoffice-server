package com.ccsw.bidoffice.projecttype;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offerdataproject.OfferDataProjectService;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;
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
        if (this.offerDataProjectService.checkExistsByProjectTypeId(id))
            throw new AlreadyExistsException();

        this.projectTypeRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectTypeEntity saveProjectType(ProjectTypeDto projectTypeDto)
            throws AlreadyExistsException, EntityNotFoundException {

        this.checkWhenProjectTypeAttributesAlreadyUsed(projectTypeDto);

        ProjectTypeEntity projectTypeEntity = null;

        if (projectTypeDto.getId() != null) {
            projectTypeEntity = get(projectTypeDto.getId());
        } else {
            projectTypeEntity = new ProjectTypeEntity();
        }

        BeanUtils.copyProperties(projectTypeDto, projectTypeEntity, "id");

        return this.projectTypeRepository.save(projectTypeEntity);
    }

    /**
     * Comprueba que no exista el mismo nombre de una tecnologia o la misma
     * prioridad.
     * 
     * @param dto Objeto DTO de la tecnología a comprobar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si el nombre o la prioridad
     *                                coinciden con algún registro de la BBDD.
     */
    private void checkWhenProjectTypeAttributesAlreadyUsed(ProjectTypeDto dto) throws AlreadyExistsException {

        ProjectTypeEntity compareProjectType = this.projectTypeRepository.getByName(dto.getName());

        compareProjectTypeGetId(dto, compareProjectType);

        compareProjectType = this.projectTypeRepository.getByPriority(dto.getPriority());

        compareProjectTypeGetId(dto, compareProjectType);

    }

    /**
     * Método que compara el ID del registro que se está editando con el existente
     * en la base de datos.
     * 
     * @param dto                Registro que se está editando.
     * @param compareProjectType Registro de la base de datos.
     * 
     * @throws AlreadyExistsException Excepción lanzada si hay error.
     */
    private void compareProjectTypeGetId(ProjectTypeDto dto, ProjectTypeEntity compareProjectType)
            throws AlreadyExistsException {

        if ((compareProjectType != null) && (dto.getId() != compareProjectType.getId()))
            throw new AlreadyExistsException();
    }

}