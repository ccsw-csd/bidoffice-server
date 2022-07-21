package com.ccsw.bidoffice.offerdataproject;

import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectEntity;

public interface OfferDataProjectService {

    OfferDataProjectEntity findByOfferId(Long id);
}
