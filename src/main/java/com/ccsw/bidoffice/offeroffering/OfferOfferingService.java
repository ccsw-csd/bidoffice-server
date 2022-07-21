package com.ccsw.bidoffice.offeroffering;

import java.util.List;

import com.ccsw.bidoffice.offeroffering.model.OfferOfferingEntity;

public interface OfferOfferingService {

    List<OfferOfferingEntity> findByOfferId(Long id);
}
