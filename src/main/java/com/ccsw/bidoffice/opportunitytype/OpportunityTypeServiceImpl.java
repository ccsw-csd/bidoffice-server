package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offer.OfferService;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

@Service
public class OpportunityTypeServiceImpl implements OpportunityTypeService {

    @Autowired
    OpportunityTypeRepository opportunityTypeRepository;

    @Autowired
    OfferService offerService;

    @Override
    public List<OpportunityTypeEntity> findAllOpportunityTypeOrderPriority() {

        return this.opportunityTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerService.checkIfExistsOffer(id))
            throw new AlreadyExistsException();

        this.opportunityTypeRepository.deleteById(id);
    }

    @Override
    public void save(OpportunityTypeDto opportunityTypeDto) throws AlreadyExistsException, EntityNotFoundException {

        this.checkIfExistsAttributes(opportunityTypeDto);

        OpportunityTypeEntity opportunityTypeEntity = null;

        if (opportunityTypeDto.getId() != null) {
            opportunityTypeEntity = getById(opportunityTypeDto);
        } else {
            opportunityTypeEntity = new OpportunityTypeEntity();
        }

        opportunityTypeEntity.setName(opportunityTypeDto.getName());
        opportunityTypeEntity.setPriority(opportunityTypeDto.getPriority());

        this.opportunityTypeRepository.save(opportunityTypeEntity);
    }

    @Override
    public OpportunityTypeEntity getById(OpportunityTypeDto opportunityTypeDto) throws EntityNotFoundException {

        return this.opportunityTypeRepository.findById(opportunityTypeDto.getId())
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Comprueba que al guardar o editar un OpportunityType, no existe otro registro
     * con el mismo nombre o prioridad.
     * 
     * @param dto Objeto DTO a cotejar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si ya existe otro registro
     *                                con el mismo nombre o prioridad.
     */
    private void checkIfExistsAttributes(OpportunityTypeDto opportunityTypeDto) throws AlreadyExistsException {

        OpportunityTypeEntity compareOpportunityType = this.opportunityTypeRepository
                .getByName(opportunityTypeDto.getName());

        compareOpportunityTypeGetId(opportunityTypeDto, compareOpportunityType);

        compareOpportunityType = this.opportunityTypeRepository.getByPriority(opportunityTypeDto.getPriority());

        compareOpportunityTypeGetId(opportunityTypeDto, compareOpportunityType);
    }

    /**
     * Método que compara el ID del registro que se está editando con el existente
     * en la base de datos.
     * 
     * @param dto               Registro que se está editando.
     * @param compareTechnology Registro de la base de datos.
     * 
     * @throws AlreadyExistsException Excepción lanzada si hay error.
     */
    private void compareOpportunityTypeGetId(OpportunityTypeDto opportunityTypeDto,
            OpportunityTypeEntity compareOpportunityType) throws AlreadyExistsException {

        if ((compareOpportunityType != null) && (opportunityTypeDto.getId() != compareOpportunityType.getId()))
            throw new AlreadyExistsException();
    }

}
