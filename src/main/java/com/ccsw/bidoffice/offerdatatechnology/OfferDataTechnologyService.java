package com.ccsw.bidoffice.offerdatatechnology;

import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

public interface OfferDataTechnologyService {

    OfferDataTechnologyEntity findByOfferId(Long id);
}
