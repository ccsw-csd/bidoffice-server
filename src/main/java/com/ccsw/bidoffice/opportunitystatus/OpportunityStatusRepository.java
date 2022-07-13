package com.ccsw.bidoffice.opportunitystatus;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;

public interface OpportunityStatusRepository extends CrudRepository<OpportunityStatusEntity, Long> {

    List<OpportunityStatusEntity> findAll(Sort sort);

}
