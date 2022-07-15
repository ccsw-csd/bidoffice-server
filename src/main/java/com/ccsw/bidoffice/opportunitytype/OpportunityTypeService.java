package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

public interface OpportunityTypeService {

    List<OpportunityTypeEntity> findAllOpportunityTypeOrderPriority();
}
