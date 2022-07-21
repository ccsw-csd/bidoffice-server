package com.ccsw.bidoffice.offerteamperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonEntity;

@Service
public class OfferTeamPersonServiceImpl implements OfferTeamPersonService {

    @Autowired
    private OfferTeamPersonRepository offerTeamPersonRepository;

    @Override
    public List<OfferTeamPersonEntity> findByOfferId(Long id) {

        return offerTeamPersonRepository.findByOfferId(id);
    }

}
