package com.ccsw.bidoffice.opportunitystatus;

import java.util.List;

import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;

public interface OpportunityStatusService {

    List<OpportunityStatusEntity> findAllOpportunityStatus();
}
