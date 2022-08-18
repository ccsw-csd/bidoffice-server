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
    public void save(OpportunityTypeDto opportunityDto) throws AlreadyExistsException, EntityNotFoundException {

        if (checkIfExistsAttributes(opportunityDto))
            throw new AlreadyExistsException();

        OpportunityTypeEntity opportunityEntity = null;

        if (opportunityDto.getId() != null) {
            opportunityEntity = getId(opportunityDto);
        } else {
            opportunityEntity = new OpportunityTypeEntity();
        }

        opportunityEntity.setName(opportunityDto.getName());
        opportunityEntity.setPriority(opportunityDto.getPriority());

        this.opportunityTypeRepository.save(opportunityEntity);
    }

    private OpportunityTypeEntity getId(OpportunityTypeDto opportunityDto) throws EntityNotFoundException {

        return this.opportunityTypeRepository.findById(opportunityDto.getId())
                .orElseThrow(EntityNotFoundException::new);
    }

    private boolean checkIfExistsAttributes(OpportunityTypeDto opportunityDto) throws AlreadyExistsException {

        boolean checkForNewItem = false, checkForAnExistingItemName = false, checkForAnExistingItemPriority = false,
                results = false;

        if (opportunityDto.getId() == null) {
            checkForNewItem = this.opportunityTypeRepository.existsByNameOrPriority(opportunityDto.getName(),
                    opportunityDto.getPriority());
        } else {
            checkForAnExistingItemName = this.opportunityTypeRepository.existsByIdNotAndName(opportunityDto.getId(),
                    opportunityDto.getName());

            checkForAnExistingItemPriority = this.opportunityTypeRepository
                    .existsByIdNotAndPriority(opportunityDto.getId(), opportunityDto.getPriority());
        }

        if (checkForNewItem || checkForAnExistingItemName || checkForAnExistingItemPriority)
            results = true;

        return results;
    }

}
