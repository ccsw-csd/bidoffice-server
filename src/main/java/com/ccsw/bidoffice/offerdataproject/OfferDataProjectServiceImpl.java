package com.ccsw.bidoffice.offerdataproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectEntity;

@Service
public class OfferDataProjectServiceImpl implements OfferDataProjectService {

    @Autowired
    private OfferDataProjectRepository offerDataProjectRepository;

    @Override
    public OfferDataProjectEntity findByOfferId(Long id) {

        return offerDataProjectRepository.findByOfferId(id);
    }

}
