package com.ccsw.bidoffice.offertracing;

import java.util.List;

import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;

public interface OfferTracingService {

    List<OfferTracingEntity> findByOfferId(Long id);
}
