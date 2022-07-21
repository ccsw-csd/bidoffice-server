package com.ccsw.bidoffice.offertechnology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyEntity;

@Service
public class OfferTechnologyServiceImpl implements OfferTechnologyService {

    @Autowired
    private OfferTechnologyRepository offerTechnologyRepository;

    @Override
    public List<OfferTechnologyEntity> findByOfferId(Long id) {

        return offerTechnologyRepository.findByOfferId(id);
    }

}
