package com.ccsw.bidoffice.offerdatateam;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamEntity;

public interface OfferDataTeamRepository extends CrudRepository<OfferDataTeamEntity, Long> {

    OfferDataTeamEntity findByOfferId(Long offerId);

    Boolean existsByOfferId(Long offerId);
}
