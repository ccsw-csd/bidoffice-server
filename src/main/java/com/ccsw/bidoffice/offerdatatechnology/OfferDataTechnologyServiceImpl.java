package com.ccsw.bidoffice.offerdatatechnology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

@Service
public class OfferDataTechnologyServiceImpl implements OfferDataTechnologyService {

    @Autowired
    private OfferDataTechnologyRepository offerDataTechnologyRepository;

    @Override
    public OfferDataTechnologyEntity findByOfferId(Long id) {

        return offerDataTechnologyRepository.findByOfferId(id);

    }

}
