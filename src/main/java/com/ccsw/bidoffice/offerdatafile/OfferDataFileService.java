package com.ccsw.bidoffice.offerdatafile;

import java.util.List;

import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

public interface OfferDataFileService {

    List<OfferDataFileEntity> findByOfferId(Long id);
}
