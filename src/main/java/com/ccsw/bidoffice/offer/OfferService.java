package com.ccsw.bidoffice.offer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

public interface OfferService {

    Page<OfferEntity> findPage(OfferSearchDto dto);

    List<String> findFirst15DistinctClientLikeFilter(String filter);

    OfferEntity getOffer(Long id);
}
