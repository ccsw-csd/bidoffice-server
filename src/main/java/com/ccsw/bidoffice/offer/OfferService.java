package com.ccsw.bidoffice.offer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

public interface OfferService {

    OfferEntity getOffer(Long id);

    List<String> findFirst15DistinctClientLikeFilter(String filter);

    Page<OfferEntity> findPage(OfferSearchDto dto);

    boolean checkIfExistsOffer(Long id);

}
