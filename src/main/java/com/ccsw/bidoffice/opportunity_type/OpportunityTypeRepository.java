package com.ccsw.bidoffice.opportunity_type;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.opportunity_type.model.OpportunityTypeEntity;

public interface OpportunityTypeRepository extends CrudRepository<OpportunityTypeEntity, Long> {

    List<OpportunityTypeEntity> findAllByOrderByPriorityAsc();
}
