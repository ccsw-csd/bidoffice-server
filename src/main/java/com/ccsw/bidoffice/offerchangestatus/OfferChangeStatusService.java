package com.ccsw.bidoffice.offerchangestatus;

import java.util.List;

import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusEntity;

public interface OfferChangeStatusService {

    List<OfferChangeStatusEntity> findByOfferId(Long id);
}
