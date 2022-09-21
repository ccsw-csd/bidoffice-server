package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

public interface OpportunityTypeRepository extends CrudRepository<OpportunityTypeEntity, Long> {

    List<OpportunityTypeEntity> findAll(Sort sort);

    OpportunityTypeEntity getByName(String name);

    OpportunityTypeEntity getByPriority(Integer priority);

}
