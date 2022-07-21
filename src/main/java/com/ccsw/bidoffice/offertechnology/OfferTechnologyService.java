package com.ccsw.bidoffice.offertechnology;

import java.util.List;

import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyEntity;

public interface OfferTechnologyService {

    List<OfferTechnologyEntity> findByOfferId(Long id);
}
