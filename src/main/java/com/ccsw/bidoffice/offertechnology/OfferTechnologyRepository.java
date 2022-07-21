package com.ccsw.bidoffice.offertechnology;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyEntity;

public interface OfferTechnologyRepository extends CrudRepository<OfferTechnologyEntity, Long> {

    List<OfferTechnologyEntity> findByOfferId(Long offerId);
}
