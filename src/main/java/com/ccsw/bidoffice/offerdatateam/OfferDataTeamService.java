package com.ccsw.bidoffice.offerdatateam;

import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamEntity;

public interface OfferDataTeamService {

    OfferDataTeamEntity findByOfferId(Long id);
}
