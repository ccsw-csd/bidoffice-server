package com.ccsw.bidoffice.offerteamperson;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonEntity;

public interface OfferTeamPersonRepository extends CrudRepository<OfferTeamPersonEntity, Long> {

    List<OfferTeamPersonEntity> findByOfferId(Long offerId);

}
