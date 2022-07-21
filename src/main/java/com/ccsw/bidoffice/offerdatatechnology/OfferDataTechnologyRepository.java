package com.ccsw.bidoffice.offerdatatechnology;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

public interface OfferDataTechnologyRepository extends CrudRepository<OfferDataTechnologyEntity, Long> {

    OfferDataTechnologyEntity findByOfferId(Long offerId);

    Boolean existsByOfferId(Long offerId);
}
