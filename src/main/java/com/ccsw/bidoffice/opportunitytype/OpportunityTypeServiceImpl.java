package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

@Service
public class OpportunityTypeServiceImpl implements OpportunityTypeService {

    @Autowired
    OpportunityTypeRepository opportunityTypeRepository;

    @Override
    public List<OpportunityTypeEntity> findAllOpportunityTypeOrderPriority() {

        return this.opportunityTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

}
