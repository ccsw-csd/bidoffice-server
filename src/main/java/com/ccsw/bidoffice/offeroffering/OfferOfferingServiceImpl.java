package com.ccsw.bidoffice.offeroffering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offeroffering.model.OfferOfferingEntity;

@Service
public class OfferOfferingServiceImpl implements OfferOfferingService {

    @Autowired
    private OfferOfferingRepository offerOfferingRepository;

    @Override
    public List<OfferOfferingEntity> findByOfferId(Long id) {

        return offerOfferingRepository.findByOfferId(id);
    }

}
