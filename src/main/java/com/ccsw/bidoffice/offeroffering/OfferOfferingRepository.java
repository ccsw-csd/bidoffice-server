package com.ccsw.bidoffice.offeroffering;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offeroffering.model.OfferOfferingEntity;

public interface OfferOfferingRepository extends CrudRepository<OfferOfferingEntity, Long> {

    List<OfferOfferingEntity> findByOfferId(Long offerId);

}
