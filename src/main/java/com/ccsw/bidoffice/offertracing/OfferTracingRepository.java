package com.ccsw.bidoffice.offertracing;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;

public interface OfferTracingRepository extends CrudRepository<OfferTracingEntity, Long> {

    List<OfferTracingEntity> findByOfferId(Long offerId);

}
