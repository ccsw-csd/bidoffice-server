package com.ccsw.bidoffice.offertracing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;

@Service
public class OfferTracingServiceImpl implements OfferTracingService {

    @Autowired
    private OfferTracingRepository offerTracingRepository;

    @Override
    public List<OfferTracingEntity> findByOfferId(Long id) {

        return offerTracingRepository.findByOfferId(id);
    }

}
