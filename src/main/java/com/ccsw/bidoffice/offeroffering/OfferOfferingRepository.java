package com.ccsw.bidoffice.offeroffering;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offeroffering.model.OfferOfferingEntity;

public interface OfferOfferingRepository extends CrudRepository<OfferOfferingEntity, Long> {
	
	boolean existsByOfferingId(Long id);

}
