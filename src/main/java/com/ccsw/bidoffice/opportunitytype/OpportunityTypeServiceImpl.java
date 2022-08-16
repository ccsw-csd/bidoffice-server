package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offer.OfferService;
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

}
