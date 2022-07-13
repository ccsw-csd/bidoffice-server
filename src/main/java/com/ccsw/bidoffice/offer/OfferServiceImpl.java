package com.ccsw.bidoffice.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Page<OfferEntity> findPage(OfferSearchDto dto) {

        return this.offerRepository.findAll(dto.getPageable());
    }

}