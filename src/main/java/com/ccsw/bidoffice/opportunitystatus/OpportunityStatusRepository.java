package com.ccsw.bidoffice.opportunity_status;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.opportunity_status.model.OpportunityStatusEntity;

public interface OpportunityStatusRepository extends CrudRepository<OpportunityStatusEntity, Long> {

    List<OpportunityStatusEntity> findAllByOrderByPriorityAsc();

}
