package com.ccsw.bidoffice.offerdatateam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamEntity;

@Service
public class OfferDataTeamServiceImpl implements OfferDataTeamService {

    @Autowired
    private OfferDataTeamRepository offerDataTeamRepository;

    @Override
    public OfferDataTeamEntity findByOfferId(Long id) {

        return offerDataTeamRepository.findByOfferId(id);

    }

}
