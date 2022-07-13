package com.ccsw.bidoffice.opportunitystatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;

@Service
public class OpportunityStatusServiceImpl implements OpportunityStatusService {

    @Autowired
    OpportunityStatusRepository opportunityStatusRepository;

    @Override
    public List<OpportunityStatusEntity> findAllOpportunityStatusOrderPriority() {

        return this.opportunityStatusRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

}
