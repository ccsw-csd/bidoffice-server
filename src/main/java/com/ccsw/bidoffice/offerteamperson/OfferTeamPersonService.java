package com.ccsw.bidoffice.offerteamperson;

import java.util.List;

import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonEntity;

public interface OfferTeamPersonService {

    List<OfferTeamPersonEntity> findByOfferId(Long id);
}
