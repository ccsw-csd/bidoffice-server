package com.ccsw.bidoffice.offerchangestatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusEntity;

@Service
public class OfferChangeStatusServiceImpl implements OfferChangeStatusService {

    @Autowired
    private OfferChangeStatusRepository offerChangeStatusRepository;


    @Override
    public List<OfferChangeStatusEntity> findByOfferId(Long id) {
        
        return this.offerChangeStatusRepository.findByOfferId(id);
    }

}
