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

        if (checkIfExistsAttributes(opportunityTypeDto))
            throw new AlreadyExistsException();

        OpportunityTypeEntity opportunityTypeEntity = null;

        if (opportunityTypeDto.getId() != null) {
            opportunityTypeEntity = getId(opportunityTypeDto);
        } else {
            opportunityTypeEntity = new OpportunityTypeEntity();
        }

        opportunityTypeEntity.setName(opportunityTypeDto.getName());
        opportunityTypeEntity.setPriority(opportunityTypeDto.getPriority());

        this.opportunityTypeRepository.save(opportunityTypeEntity);
    }

    private OpportunityTypeEntity getId(OpportunityTypeDto opportunityTypeDto) throws EntityNotFoundException {

        return this.opportunityTypeRepository.findById(opportunityTypeDto.getId())
                .orElseThrow(EntityNotFoundException::new);
    }

    private boolean checkIfExistsAttributes(OpportunityTypeDto opportunityTypeDto) throws AlreadyExistsException {

        boolean checkForNewItem = false;
        boolean checkForAnExistingItemName = false;
        boolean checkForAnExistingItemPriority = false;
        boolean results = false;

        if (opportunityTypeDto.getId() == null) {
            checkForNewItem = this.opportunityTypeRepository.existsByNameOrPriority(opportunityTypeDto.getName(),
                    opportunityTypeDto.getPriority());
        } else {
            checkForAnExistingItemName = this.opportunityTypeRepository.existsByIdNotAndName(opportunityTypeDto.getId(),
                    opportunityTypeDto.getName());

            checkForAnExistingItemPriority = this.opportunityTypeRepository
                    .existsByIdNotAndPriority(opportunityTypeDto.getId(), opportunityTypeDto.getPriority());
        }

        if (checkForNewItem || checkForAnExistingItemName || checkForAnExistingItemPriority)
            results = true;

        return results;
    }

}
