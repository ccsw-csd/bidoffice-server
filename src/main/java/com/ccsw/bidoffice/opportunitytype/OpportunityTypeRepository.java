package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

public interface OpportunityTypeRepository extends CrudRepository<OpportunityTypeEntity, Long> {

    List<OpportunityTypeEntity> findAll(Sort sort);

    boolean existsByIdNotAndPriority(Long id, Integer priority);

    boolean existsByIdNotAndName(Long id, String name);

    boolean existsByNameOrPriority(String name, Integer priority);

}
