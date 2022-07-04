package com.ccsw.bidoffice.offer;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

public interface OfferService {

    Page<OfferEntity> findPage(OfferSearchDto dto);
}
