package com.ccsw.bidoffice.opportunitystatus;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;

public interface OpportunityStatusRepository extends CrudRepository<OpportunityStatusEntity, Long> {

}
